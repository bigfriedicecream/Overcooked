package com.bigfriedicecream.overcooked.presenters

import android.content.Context
import android.preference.PreferenceManager

import com.bigfriedicecream.overcooked.interfaces.IRecipeContract
import com.bigfriedicecream.overcooked.models.RecipeModel
import com.bigfriedicecream.overcooked.observables.RecipeRepository

import java.util.Observable
import java.util.Observer


class RecipePresenter(private val view:IRecipeContract.View) : IRecipeContract.Presenter, Observer {
    private val recipeRepository:RecipeRepository = RecipeRepository.instance
    private var recipeModel:RecipeModel = RecipeModel()
    private var id:String? = null
    private var adjustedQuantity:Int = -1

    override fun start(context:Context?, id:String?, restore:Boolean?) {
        this.id = id

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        if (restore == true) {
            adjustedQuantity = preferences.getInt("adjustedQuantity", -1)

        } else {
            val preferenceEditor = preferences.edit()
            preferenceEditor.putInt("adjustedQuantity", -1)
            preferenceEditor.apply()
        }

        recipeRepository.addObserver(this)
        recipeRepository.load(context)
    }

    override fun stop() {
        recipeRepository.deleteObserver(this)
    }

    override fun adjustQuantityClick() {
        view.showAdjustQuantityDialog(recipeModel)
    }

    override fun updateQuantity(newQuantity:Int, context:Context?) {
        adjustedQuantity = newQuantity
        val preferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        preferences.putInt("adjustedQuantity", adjustedQuantity)
        preferences.apply()

        val recipeQuantity:Int = if (recipeModel.serves > 0) recipeModel.serves else recipeModel.makes
        val multiplier:Double = adjustedQuantity.toDouble() / recipeQuantity.toDouble()
        recipeModel.multiplier = multiplier
        view.render(recipeModel)
    }

    override fun update(observable:Observable, o:Any?) {
        if (observable is RecipeRepository) {
            recipeModel = recipeRepository.getRecipeById(id)

            if (adjustedQuantity != -1) {
                val recipeQuantity:Int = if (recipeModel.serves > 0) recipeModel.serves else recipeModel.makes
                val multiplier:Double = adjustedQuantity.toDouble() / recipeQuantity.toDouble()
                recipeModel.multiplier = multiplier
            }

            view.render(recipeModel)
        }
    }
}

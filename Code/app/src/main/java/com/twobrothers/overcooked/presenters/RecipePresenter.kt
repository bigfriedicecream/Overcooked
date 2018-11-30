package com.twobrothers.overcooked.presenters

import android.content.Context
import android.preference.PreferenceManager
import com.twobrothers.overcooked.Overcooked

import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.models.RecipeModel
import com.twobrothers.overcooked.observables.RecipeRepository
import com.twobrothers.overcooked.utils.IO

import java.util.Observable
import java.util.Observer


class RecipePresenter(private val view:IRecipeContract.View) : IRecipeContract.Presenter, Observer {
    private val recipeRepository:RecipeRepository = RecipeRepository.instance
    private var recipeModel:RecipeModel = RecipeModel()
    private var id:String? = null
    private var adjustedQuantity:Int = -1

    override fun start(id:String?, restore:Boolean?) {
        this.id = id

        if (restore == true) {
            adjustedQuantity = IO.getInt("adjustedQuantity")

        } else {
            IO.putInt("adjustedQuantity", -1)
        }

        recipeRepository.addObserver(this)
        recipeRepository.load()
    }

    override fun stop() {
        recipeRepository.deleteObserver(this)
    }

    override fun adjustQuantityClick() {
        view.showAdjustQuantityDialog(recipeModel)
    }

    override fun updateQuantity(newQuantity:Int) {
        adjustedQuantity = newQuantity
        IO.putInt("adjustedQuantity", adjustedQuantity)

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

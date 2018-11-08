package com.bigfriedicecream.overcooked.presenters

import android.content.Context
import com.bigfriedicecream.overcooked.interfaces.IRecipeContract
import com.bigfriedicecream.overcooked.models.RecipeModel
import com.bigfriedicecream.overcooked.observables.RecipeRepository

import java.util.Observable
import java.util.Observer

class RecipePresenter(private val view:IRecipeContract.View) : IRecipeContract.Presenter, Observer {
    private val recipeRepository:RecipeRepository = RecipeRepository.instance
    private var recipeModel:RecipeModel? = null
    private var id:String? = null

    override fun start(context:Context?, id:String?) {
        this.id = id
        recipeRepository.addObserver(this)
        recipeRepository.load(context)
    }

    override fun stop() {
        recipeRepository.deleteObserver(this)
    }

    override fun adjustQuantityClick() {
        view.showAdjustQuantityDialog(recipeModel)
    }

    override fun update(observable:Observable, o:Any?) {
        if (observable is RecipeRepository) {
            recipeModel = recipeRepository.getRecipeById(id)
            view.render(recipeModel)
        }
    }
}

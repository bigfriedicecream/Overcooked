package com.bigfriedicecream.overcooked.presenters

import android.content.Context

import com.bigfriedicecream.overcooked.interfaces.IRecipeListContract
import com.bigfriedicecream.overcooked.models.RecipeDataModel
import com.bigfriedicecream.overcooked.models.RecipeResponseDataModel
import com.bigfriedicecream.overcooked.observables.RecipeRepository
import java.util.Observable
import java.util.Observer

class RecipeListPresenter(private val view:IRecipeListContract.View) : IRecipeListContract.Presenter, Observer {
    private val recipeRepository:RecipeRepository = RecipeRepository.getInstance()

    override fun start(c: Context) {
        recipeRepository.addObserver(this)
        val model:RecipeResponseDataModel? = recipeRepository.get()
        if (model != null) {
            view.render(model.recipes)
        }
    }

    override fun stop() {
        recipeRepository.deleteObserver(this)
    }

    override fun update(observable:Observable, o:Any) {
        if (observable is RecipeRepository) {
            val model:RecipeResponseDataModel? = recipeRepository.get()
            if (model != null) {
                view.render(model.recipes)
            }
        }
    }

}

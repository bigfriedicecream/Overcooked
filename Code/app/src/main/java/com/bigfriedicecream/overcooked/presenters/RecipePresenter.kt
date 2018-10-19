package com.bigfriedicecream.overcooked.presenters

import com.bigfriedicecream.overcooked.interfaces.IRecipeContract
import com.bigfriedicecream.overcooked.models.RecipeModel
import com.bigfriedicecream.overcooked.observables.RecipeRepository

import java.util.Observable
import java.util.Observer

class RecipePresenter(private val view:IRecipeContract.View) : IRecipeContract.Presenter, Observer {
    private val recipeRepository:RecipeRepository = RecipeRepository.instance

    override fun start(id:String?) {
        recipeRepository.addObserver(this)
        val recipe:RecipeModel = recipeRepository.getRecipeById(id)
        view.render(recipe)
    }

    override fun stop() {
        recipeRepository.deleteObserver(this)
    }

    /*override fun load(id:String) {
        val recipe:RecipeDataModel? = recipeRepository.get()?.recipes?.get(id)
        if (recipe != null) {
            view.render(recipe)
        }
    }*/

    override fun update(observable:Observable, o:Any) {

    }
}

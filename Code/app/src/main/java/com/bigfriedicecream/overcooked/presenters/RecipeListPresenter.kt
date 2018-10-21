package com.bigfriedicecream.overcooked.presenters

import android.content.Context

import com.bigfriedicecream.overcooked.interfaces.IRecipeListContract
import com.bigfriedicecream.overcooked.models.RecipeModel
import com.bigfriedicecream.overcooked.observables.RecipeRepository
import java.util.Observable
import java.util.Observer

class RecipeListPresenter(private val view:IRecipeListContract.View) : IRecipeListContract.Presenter, Observer {
    private val recipeRepository:RecipeRepository = RecipeRepository.instance

    override fun start(c: Context) {
        recipeRepository.addObserver(this)
        val recipeList:HashMap<String, RecipeModel> = recipeRepository.getRecipeList()
        view.render(recipeList)
    }

    override fun stop() {
        recipeRepository.deleteObserver(this)
    }

    override fun update(observable:Observable, o:Any) {
        
    }

}

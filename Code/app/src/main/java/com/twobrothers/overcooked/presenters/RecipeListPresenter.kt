package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.models.RecipeModel
import com.twobrothers.overcooked.observables.RecipeRepository
import java.util.Observable
import java.util.Observer


class RecipeListPresenter(private val view:IRecipeListContract.View) : IRecipeListContract.Presenter, Observer {
    private val recipeRepository:RecipeRepository = RecipeRepository.instance

    override fun start() {
        recipeRepository.addObserver(this)
        view.showLoading()
        recipeRepository.load()
    }

    override fun stop() {
        recipeRepository.deleteObserver(this)
    }

    override fun update(observable:Observable, o:Any?) {
        if (observable is RecipeRepository) {
            val recipeList:HashMap<String, RecipeModel> = recipeRepository.getRecipeList()
            view.render(recipeList)
        }
    }

}

package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.models.RecipeModel
import com.twobrothers.overcooked.observables.RecipeRepository
import java.util.Observable
import java.util.Observer


class RecipeListPresenter(private val view:IRecipeListContract.View) : IRecipeListContract.Presenter {

    override fun onStart() {

    }

    override fun onStop() {

    }

}

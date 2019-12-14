package com.twobrothers.overcooked.recipelibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twobrothers.overcooked.core.OnDataSourceResult
import com.twobrothers.overcooked.core.getRecipes
import com.twobrothers.overcooked.recipedetails.models.Recipe

class RecipeLibraryViewModel {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        // TODO: Handle on failure listener
        getRecipes(object : OnDataSourceResult<List<Recipe>> {
            override fun onSuccess(result: List<Recipe>) {
                _recipes.value = result
            }
        })
    }
}
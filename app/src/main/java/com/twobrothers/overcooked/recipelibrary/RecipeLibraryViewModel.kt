package com.twobrothers.overcooked.recipelibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twobrothers.overcooked.core.Event
import com.twobrothers.overcooked.core.OnDataSourceResult
import com.twobrothers.overcooked.core.getRecipes
import com.twobrothers.overcooked.recipedetails.models.Recipe

class RecipeLibraryViewModel {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _navigateToRecipeDetails = MutableLiveData<Event<String>>()
    val navigateToRecipeDetails: LiveData<Event<String>> = _navigateToRecipeDetails

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

    fun onRecipeClick(id: String) {
        _navigateToRecipeDetails.value = Event(id)
    }
}
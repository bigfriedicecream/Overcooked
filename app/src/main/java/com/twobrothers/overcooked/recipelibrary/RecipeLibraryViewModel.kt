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

    private val _isRecipeLibraryVisible = MutableLiveData<Boolean>()
    val isRecipeLibraryVisible: LiveData<Boolean> = _isRecipeLibraryVisible

    private val _isLoadingIndicatorVisible = MutableLiveData<Boolean>()
    val isLoadingIndicatorVisible: LiveData<Boolean> = _isLoadingIndicatorVisible

    private val _navigateToRecipeDetails = MutableLiveData<Event<String>>()
    val navigateToRecipeDetails: LiveData<Event<String>> = _navigateToRecipeDetails

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        showLoadingIndicator()
        getRecipes(object : OnDataSourceResult<List<Recipe>> {
            override fun onSuccess(result: List<Recipe>) {
                handleSuccess(result)
                showRecipeLibrary()
            }

            override fun onFailure(result: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun handleSuccess(recipes: List<Recipe>) {
        _recipes.value = recipes
    }

    private fun showRecipeLibrary() {
        _isLoadingIndicatorVisible.value = false
        _isRecipeLibraryVisible.value = true
    }

    private fun showLoadingIndicator() {
        _isRecipeLibraryVisible.value = false
        _isLoadingIndicatorVisible.value = true
    }

    fun onRecipeClick(id: String) {
        _navigateToRecipeDetails.value = Event(id)
    }
}
package com.twobrothers.overcooked.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twobrothers.overcooked.core.OnDataSourceResult
import com.twobrothers.overcooked.core.getRecipe
import com.twobrothers.overcooked.recipedetails.models.Recipe

class RecipeDetailsViewModel(id: String) {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _serves = MutableLiveData<String>()
    val serves: LiveData<String> = _serves

    private val _prepTime = MutableLiveData<String>()
    val prepTime: LiveData<String> = _prepTime

    private val _cookTime = MutableLiveData<String>()
    val cookTime: LiveData<String> = _cookTime

    private val _isRecipeDetailsVisible = MutableLiveData<Boolean>()
    val isRecipeDetialsVisible: LiveData<Boolean> = _isRecipeDetailsVisible

    private val _isLoadingIndicatorVisible = MutableLiveData<Boolean>()
    val isLoadingIndicatorVisible: LiveData<Boolean> = _isLoadingIndicatorVisible

    init {
        showLoadingIndicator()
        getRecipe(id, object : OnDataSourceResult<Recipe> {
            override fun onSuccess(result: Recipe) {
                handleSuccess(result)
                showRecipeDetails()
            }

            override fun onFailure(result: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun handleSuccess(recipe: Recipe) {
        // TODO: Get serves, prepTime, cookTime from string resource
        _title.value = recipe.title
        _serves.value = "Serves: ${recipe.serves}"
        _prepTime.value = "Prep: ${recipe.prepTime}"
        _cookTime.value = "Cook: ${recipe.cookTime}"
    }

    private fun showLoadingIndicator() {
        _isRecipeDetailsVisible.value = false
        _isLoadingIndicatorVisible.value = true
    }

    private fun showRecipeDetails() {
        _isLoadingIndicatorVisible.value = false
        _isRecipeDetailsVisible.value = true
    }

}
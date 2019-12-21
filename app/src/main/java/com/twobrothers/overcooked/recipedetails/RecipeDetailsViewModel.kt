package com.twobrothers.overcooked.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twobrothers.overcooked.core.FirebaseApiDataSource
import com.twobrothers.overcooked.recipedetails.models.Recipe
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(id: String) : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _serves = MutableLiveData<String>()
    val serves: LiveData<String> = _serves

    private val _prepTime = MutableLiveData<String>()
    val prepTime: LiveData<String> = _prepTime

    private val _cookTime = MutableLiveData<String>()
    val cookTime: LiveData<String> = _cookTime

    private val _method = MutableLiveData<List<String>>()
    val method: LiveData<List<String>> = _method

    private val _isRecipeDetailsVisible = MutableLiveData<Boolean>()
    val isRecipeDetialsVisible: LiveData<Boolean> = _isRecipeDetailsVisible

    private val _isLoadingIndicatorVisible = MutableLiveData<Boolean>()
    val isLoadingIndicatorVisible: LiveData<Boolean> = _isLoadingIndicatorVisible

    init {
        showLoadingIndicator()
        // TODO: Mikey - inject data source, use dispatcherProvider.computation
        val dataSource = FirebaseApiDataSource()
        viewModelScope.launch {
            val recipeList = dataSource.getRecipe(id)
            if (recipeList != null) {
                handleSuccess(recipeList)
                showRecipeDetails()
            }
        }
    }

    private fun handleSuccess(recipe: Recipe) {
        // TODO: Get serves, prepTime, cookTime from string resource
        _title.value = recipe.title
        _serves.value = "Serves: ${recipe.serves}"
        _prepTime.value = "Prep: ${recipe.prepTime}"
        _cookTime.value = "Cook: ${recipe.cookTime}"
        _method.value = recipe.method
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
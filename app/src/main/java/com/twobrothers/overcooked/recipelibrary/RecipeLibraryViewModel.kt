package com.twobrothers.overcooked.recipelibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twobrothers.overcooked.core.framework.Event
import com.twobrothers.overcooked.core.datasource.FirebaseApiDataSource
import com.twobrothers.overcooked.core.dagger.DaggerFirebaseApiDataSourceComponent
import com.twobrothers.overcooked.recipelibrary.models.RecipeSummary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecipeLibraryViewModel : ViewModel() {

    private val dataSource: FirebaseApiDataSource =
        DaggerFirebaseApiDataSourceComponent.create().getDataSource()

    private val _recipes = MutableLiveData<List<RecipeSummary>>()
    val recipes: LiveData<List<RecipeSummary>> = _recipes

    private val _isRecipeLibraryVisible = MutableLiveData<Boolean>()
    val isRecipeLibraryVisible: LiveData<Boolean> = _isRecipeLibraryVisible

    private val _isLoadingIndicatorVisible = MutableLiveData<Boolean>()
    val isLoadingIndicatorVisible: LiveData<Boolean> = _isLoadingIndicatorVisible

    private val _navigateToRecipeDetails = MutableLiveData<Event<String>>()
    val navigateToRecipeDetails: LiveData<Event<String>> = _navigateToRecipeDetails

    private val _isErrorVisible = MutableLiveData<Boolean>()
    val isErrorVisible: LiveData<Boolean> = _isErrorVisible

    init {
        loadRecipes()
    }

    private fun loadRecipes(withDelay: Boolean = false) {
        showLoadingIndicator()
        viewModelScope.launch {
            if (withDelay) {
                delay(1750)
            }
            val recipeList = dataSource.getRecipes()
            if (recipeList != null) {
                handleSuccess(recipeList)
                showRecipeLibrary()
            } else {
                showError()
            }
        }

    }

    private fun handleSuccess(recipes: List<RecipeSummary>) {
        _recipes.value = recipes
    }

    private fun showRecipeLibrary() {
        _isLoadingIndicatorVisible.value = false
        _isErrorVisible.value = false
        _isRecipeLibraryVisible.value = true
    }

    private fun showLoadingIndicator() {
        _isRecipeLibraryVisible.value = false
        _isErrorVisible.value = false
        _isLoadingIndicatorVisible.value = true
    }

    private fun showError() {
        _isRecipeLibraryVisible.value = false
        _isLoadingIndicatorVisible.value = false
        _isErrorVisible.value = true
    }

    fun onRecipeClick(id: String) {
        _navigateToRecipeDetails.value =
            Event(id)
    }

    fun onRetryClick() {
        loadRecipes(true)
    }
}
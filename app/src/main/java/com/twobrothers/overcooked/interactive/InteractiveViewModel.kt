package com.twobrothers.overcooked.interactive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twobrothers.overcooked.core.datasource.FirebaseApiDataSource
import com.twobrothers.overcooked.core.dagger.DaggerFirebaseApiDataSourceComponent
import com.twobrothers.overcooked.recipedetails.models.InteractiveStep
import com.twobrothers.overcooked.recipedetails.models.Recipe
import kotlinx.coroutines.launch

class InteractiveViewModel(id: String) : ViewModel() {

    private val dataSource: FirebaseApiDataSource =
        DaggerFirebaseApiDataSourceComponent.create().getDataSource()

    //region state properties

    private val _interactive = MutableLiveData<Pair<List<InteractiveStep>, Int>>()
    val interactive: LiveData<Pair<List<InteractiveStep>, Int>> = _interactive

    private val _isInteractiveVisible = MutableLiveData<Boolean>()
    val isInteractiveVisible: LiveData<Boolean> = _isInteractiveVisible

    private val _isLoadingIndicatorVisible = MutableLiveData<Boolean>()
    val isLoadingIndicatorVisible: LiveData<Boolean> = _isLoadingIndicatorVisible

    //endregion

    init {
        showLoadingIndicator()
        viewModelScope.launch {
            val recipeList = dataSource.getRecipe(id)
            if (recipeList != null) {
                handleSuccess(recipeList)
                showInteractive()
            }
        }
    }

    //region private

    private fun handleSuccess(recipe: Recipe) {
        _interactive.value = Pair(recipe.interactive, recipe.serves)
    }

    private fun showInteractive() {
        _isLoadingIndicatorVisible.value = false
        _isInteractiveVisible.value = true
    }

    private fun showLoadingIndicator() {
        _isInteractiveVisible.value = false
        _isLoadingIndicatorVisible.value = true
    }

    //endregion

}
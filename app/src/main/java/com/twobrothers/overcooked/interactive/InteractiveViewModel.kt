package com.twobrothers.overcooked.interactive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twobrothers.overcooked.core.datasource.FirebaseApiDataSource
import com.twobrothers.overcooked.core.dagger.DaggerFirebaseApiDataSourceComponent
import com.twobrothers.overcooked.recipedetails.models.Recipe
import kotlinx.coroutines.launch

class InteractiveViewModel(id: String) : ViewModel() {

    private val dataSource: FirebaseApiDataSource =
        DaggerFirebaseApiDataSourceComponent.create().getDataSource()

    init {
        viewModelScope.launch {
            val recipeList = dataSource.getRecipe(id)
            if (recipeList != null) {
                handleSuccess(recipeList)
            }
        }
    }

    private fun handleSuccess(recipe: Recipe) {

    }

}
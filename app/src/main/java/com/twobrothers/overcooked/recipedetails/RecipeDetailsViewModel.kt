package com.twobrothers.overcooked.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twobrothers.overcooked.core.OnDataSourceResult
import com.twobrothers.overcooked.core.getRecipe
import com.twobrothers.overcooked.recipedetails.models.Recipe

class RecipeDetailsViewModel(id: String) {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    init {
        getRecipe(id, object : OnDataSourceResult<Recipe> {
            override fun onSuccess(result: Recipe) {
                handleSuccess(result)
            }

            override fun onFailure(result: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun handleSuccess(recipe: Recipe) {
        _title.value = recipe.title
    }

}
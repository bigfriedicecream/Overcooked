package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.models.recipelist.RecipeListResponseModel
import io.reactivex.Single

object RecipeListManager {
    var recipes = mutableListOf<RecipeResponseModel.Recipe>()
        private set
    var request: Single<RecipeListResponseModel>? = null
        private set
    var requestInProgress = false
        get() {
            return request != null
        }
        private set
    var lastPage: Boolean = false
        private set
    var currentPage: Int = -1
        private set

    fun loadRecipes(): Single<RecipeListResponseModel>? {
        if (lastPage) {
            return null
        }
        if (request != null) {
            return request
        }
        request = ApiClient.getRecipesAt(currentPage + 1)
                .doOnSuccess {
                    recipes.addAll(it.data.recipes)
                    currentPage += 1
                    lastPage = it.data.lastPage
                    request = null
                }

        return request
    }
}
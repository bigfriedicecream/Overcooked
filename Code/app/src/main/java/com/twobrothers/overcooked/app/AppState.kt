package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.models.recipelist.RecipeListResponseModel
import io.reactivex.Single

object AppState {
    object RecipeList {
        var recipes = mutableListOf<RecipeResponseModel.Recipe>()
            private set
        var request: Single<RecipeListResponseModel>? = null
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

    object Recipe {
        const val MAX_QUANTITY: Int = 100
        var data: RecipeModel? = null
            private set
        var activeQuantity: Int = -1
            private set

        fun load(id: String): Single<RecipeModel> {
            return ApiClient
                    .getRecipe(id)
                    .doOnSuccess {
                        data = it
                        activeQuantity = it?.serves ?: it?.makes ?: 0
                    }
        }

        fun updateActiveQuantity(quantity: Int) {
            if (quantity in 1..MAX_QUANTITY) {
                activeQuantity = quantity
            }
        }

        fun reset() {
            data = null
            activeQuantity = -1
        }
    }
}
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

    private fun getRecipesAt(page: Int): Single<RecipeListResponseModel> {
        val cache = CacheService.getRecipeList(page)
        val request = ApiClient.getRecipesAt(page)
                .doAfterSuccess {
                    CacheService.putRecipeList(page, it)
                    it.data.recipes.forEach {
                        CacheService.putRecipe(it)
                    }
                    it.data.food.forEach {
                        CacheService.putFood(it.value)
                    }

                }
                .onErrorResumeNext {
                    Single.create {
                        if (cache != null) {
                            it.onSuccess(cache.data)
                        }
                    }
                }

        if (cache != null && cache.isFresh()) {
            return Single.create {
                it.onSuccess(cache.data)
            }
        }

        if (cache != null && cache.isExpiring()) {
            request.subscribe()
            return Single.create {
                it.onSuccess(cache.data)
            }
        }

        return request
    }

    fun loadRecipes(): Single<RecipeListResponseModel>? {
        if (lastPage) {
            return null
        }
        if (request != null) {
            return request
        }
        request = getRecipesAt(currentPage + 1)
                .doOnSuccess {
                    recipes.addAll(it.data.recipes)
                    currentPage += 1
                    lastPage = it.data.lastPage
                    request = null
                }

        return request
    }
}
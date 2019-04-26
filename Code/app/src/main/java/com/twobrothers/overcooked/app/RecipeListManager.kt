package com.twobrothers.overcooked.app

import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.app.RecipeManager.putRecipe
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.models.recipelist.RecipeListResponseModel
import com.twobrothers.overcooked.utils.CacheItem
import io.reactivex.Single

object RecipeListManager {

    private const val CACHE_LENGTH: Int = 1000 * 60 * 60 * 8

    var recipes = mutableListOf<RecipeResponseModel.Recipe>()
        private set
    var request: Single<RecipeListResponseModel>? = null
    var lastPage: Boolean = false
        private set
    var currentPage: Int = -1
        private set

    private fun getRecipesAt(page: Int): Single<RecipeListResponseModel> {
        val cache = CacheService.get("recipeList-$page", object: TypeToken<CacheItem<RecipeListResponseModel>>(){}.type, RecipeListResponseModel::class.java)
        val request = ApiClient.getRecipesAt(page)
                .doAfterSuccess {
                    putRecipeList(page, it)
                    it.data.recipes.forEach {
                        putRecipe(it)
                    }
                    it.data.food.forEach {
                        FoodManager.putFood(it.value)
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

    private fun putRecipeList(page: Int, model: RecipeListResponseModel) {
        CacheService.put("recipeList-$page", model, CACHE_LENGTH)
    }

    fun loadRecipes(): Single<RecipeListResponseModel>? {
        if (lastPage) {
            return null
        }
        if (request != null) {
            return request
        }
        request = getRecipesAt(currentPage + 1)
                .doAfterSuccess {
                    recipes.addAll(it.data.recipes)
                    currentPage += 1
                    lastPage = it.data.lastPage
                    request = null
                }

        return request
    }
}
package com.twobrothers.overcooked.app

import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.utils.CacheItem
import io.reactivex.Single

object RecipeManager {

    fun getRecipe(id: String): Single<RecipeModel> {
        val recipeCache = CacheService.get("recipe-$id", object: TypeToken<CacheItem<RecipeResponseModel.Recipe>>(){}.type, RecipeResponseModel.Recipe::class.java)
        val request = ApiClient.getRecipe(id)
                .map {
                    CacheService.put("recipe-${it.data.recipe.id}", it.data.recipe, 1000 * 60)
                    val recipe = it.data.recipe
                    val ingredients = ArrayList<JsonObject>()
                    RecipeModel(
                            recipe.id,
                            recipe.title,
                            recipe.prepTime,
                            recipe.cookTime,
                            ingredients,
                            recipe.method,
                            recipe.referenceUrl,
                            recipe.imageUrl,
                            recipe.lastUpdated,
                            recipe.serves,
                            recipe.makes
                    )
                }

        recipeCache ?: return request

        if (recipeCache.isFresh()) {
            val recipe = recipeCache.data
            val ingredients = ArrayList<JsonObject>()
            return Single.create {
                it.onSuccess(
                        RecipeModel(
                                recipe.id,
                                recipe.title,
                                recipe.prepTime,
                                recipe.cookTime,
                                ingredients,
                                recipe.method,
                                recipe.referenceUrl,
                                recipe.imageUrl,
                                recipe.lastUpdated,
                                recipe.serves,
                                recipe.makes
                        )
                )
            }
        }

        if (recipeCache.isExpiring()) {
            request.subscribe()
            val recipe = recipeCache.data
            val ingredients = ArrayList<JsonObject>()
            return Single.create {
                it.onSuccess(
                        RecipeModel(
                                recipe.id,
                                recipe.title,
                                recipe.prepTime,
                                recipe.cookTime,
                                ingredients,
                                recipe.method,
                                recipe.referenceUrl,
                                recipe.imageUrl,
                                recipe.lastUpdated,
                                recipe.serves,
                                recipe.makes
                        )
                )
            }
        }

        return request
    }
}
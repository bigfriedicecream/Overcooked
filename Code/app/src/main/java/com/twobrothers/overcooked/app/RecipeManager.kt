package com.twobrothers.overcooked.app

import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.lookups.LookupIngredientType
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.utils.CacheItem
import io.reactivex.Single

object RecipeManager {

    fun getRecipe(id: String): Single<RecipeModel> {
        val recipeCache = CacheService.get("recipe-$id", object: TypeToken<CacheItem<RecipeResponseModel.Recipe>>(){}.type, RecipeResponseModel.Recipe::class.java)

        fun getRecipeModelFromResponse(recipe: RecipeResponseModel.Recipe): RecipeModel {
            val ingredients = ArrayList<RecipeModel.Ingredient>()

            recipe.ingredientSections.forEach {
                if (!it.heading.isNullOrBlank()) {
                    ingredients.add(RecipeModel.Heading(LookupIngredientType.Heading.id, it.heading))
                }
            }

            return RecipeModel(
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

        val request = ApiClient.getRecipe(id)
                .map {
                    CacheService.put("recipe-${it.data.recipe.id}", it.data.recipe, 1000 * 60)
                    getRecipeModelFromResponse(it.data.recipe)
                }

        recipeCache ?: return request

        if (recipeCache.isFresh()) {
            return Single.create {
                it.onSuccess(
                        getRecipeModelFromResponse(recipeCache.data)
                )
            }
        }

        if (recipeCache.isExpiring()) {
            request.subscribe()
            return Single.create {
                it.onSuccess(
                        getRecipeModelFromResponse(recipeCache.data)
                )
            }
        }

        return request
    }
}
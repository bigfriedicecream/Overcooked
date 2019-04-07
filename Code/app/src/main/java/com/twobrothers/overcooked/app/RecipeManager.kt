package com.twobrothers.overcooked.app

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.lookups.LookupIngredientType
import com.twobrothers.overcooked.models.food.FoodResponseModel
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.models.recipelist.RecipeListResponseModel
import com.twobrothers.overcooked.utils.CacheItem
import io.reactivex.Single

object RecipeManager {

    fun getRecipesAt(page: Int): Single<RecipeListResponseModel> {
        val cache = CacheService.get("recipeList-$page", object: TypeToken<CacheItem<RecipeListResponseModel>>(){}.type, RecipeListResponseModel::class.java)
        val request = ApiClient.getRecipesAt(page)
                .doAfterSuccess {
                    CacheService.put("recipeList-$page", it, 1000 * 60 * 60 * 8)
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

    fun getRecipe(id: String): Single<RecipeModel> {
        val recipeCache = CacheService.get("recipe-$id", object: TypeToken<CacheItem<RecipeResponseModel.Recipe>>(){}.type, RecipeResponseModel.Recipe::class.java)

        fun getRecipeModelFromResponse(recipe: RecipeResponseModel.Recipe): RecipeModel {
            val ingredients = ArrayList<RecipeModel.Ingredient>()
            val food = HashMap<String, FoodResponseModel>()

            recipe.ingredientSections.forEach {
                if (!it.heading.isNullOrBlank()) {
                    ingredients.add(RecipeModel.Heading(LookupIngredientType.Heading.id, it.heading))
                }
                it.ingredients.forEach {
                    when (it.get("ingredientType").asInt) {
                        LookupIngredientType.FreeText.id -> ingredients.add(Gson().fromJson(it, RecipeModel.FreeText::class.java))
                        LookupIngredientType.Quantified.id -> {
                            val quantified = Gson().fromJson(it, RecipeModel.Quantified::class.java)
                            ingredients.add(quantified)
                            food[quantified.foodId] = FoodManager.getFood(quantified.foodId)!!
                        }
                    }
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
                    recipe.makes,
                    food
            )
        }

        val request = ApiClient.getRecipe(id)
                .map {
                    putRecipe(it.data.recipe)
                    it.data.food.forEach {
                        FoodManager.putFood(it.value)
                    }
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

    fun putRecipe(model: RecipeResponseModel.Recipe) {
        CacheService.put("recipe-${model.id}", model, 1000 * 60 * 60 * 24)
    }
}
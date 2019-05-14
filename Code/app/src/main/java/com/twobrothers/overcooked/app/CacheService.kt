package com.twobrothers.overcooked.app

import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.Overcooked
import com.twobrothers.overcooked.models.food.FoodResponseModel
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.models.recipelist.RecipeListResponseModel
import com.twobrothers.overcooked.utils.CacheItem
import java.lang.reflect.Type


object CacheService {

    fun getRecipeList(page: Int): CacheItem<RecipeListResponseModel>? {
        return get("recipeList-$page", object: TypeToken<CacheItem<RecipeListResponseModel>>(){}.type, RecipeListResponseModel::class.java)
    }

    fun putRecipeList(page: Int, model: RecipeListResponseModel) {
        put("recipeList-$page", model, BuildConfig.RECIPE_LIST_CACHE_LENGTH)
    }

    fun getRecipe(id: String): CacheItem<RecipeResponseModel.Recipe>? {
        return get("recipe-$id", object: TypeToken<CacheItem<RecipeResponseModel.Recipe>>(){}.type, RecipeResponseModel.Recipe::class.java)
    }

    fun putRecipe(model: RecipeResponseModel.Recipe) {
        put("recipe-${model.id}", model, BuildConfig.RECIPE_CACHE_LENGTH)
    }

    fun getFood(id: String): FoodResponseModel? {
        val cache = get("food-$id", object: TypeToken<CacheItem<FoodResponseModel>>(){}.type, FoodResponseModel::class.java)
        cache ?: return null
        return cache.data
    }

    fun putFood(model: FoodResponseModel) {
        put("food-${model.id}", model, BuildConfig.FOOD_CACHE_LENGTH)
    }

    private fun <T>get(key: String, typeToken: Type, c: Class<T>): CacheItem<T>? {
        val data = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext).getString(key, "")

        if (!data.isNullOrEmpty()) {
            return Gson().fromJson(data, typeToken)
        }

        return null
    }

    private fun <T>put(key: String, data: T, cacheLength: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext)
        val cacheItem = CacheItem(data, cacheLength)
        val collectionType = object: TypeToken<CacheItem<T>>(){}.type
        pref.edit().putString(key, Gson().toJson(cacheItem, collectionType)).apply()
    }

}
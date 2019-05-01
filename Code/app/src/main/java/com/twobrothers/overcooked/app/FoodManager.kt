package com.twobrothers.overcooked.app

import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.models.food.FoodResponseModel
import com.twobrothers.overcooked.utils.CacheItem

object FoodManager {

    fun getFood(id: String): FoodResponseModel? {
        val cache = CacheService.get("food-$id", object: TypeToken<CacheItem<FoodResponseModel>>(){}.type, FoodResponseModel::class.java)
        cache ?: return null
        return cache.data
    }

    fun putFood(model: FoodResponseModel) {
        CacheService.put("food-${model.id}", model, 1000 * 60 * 60 * 24)
    }
}
package com.twobrothers.overcooked.app

import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.Overcooked
import com.twobrothers.overcooked.models.food.FoodResponseModel
import com.twobrothers.overcooked.utils.CacheItem
import java.lang.reflect.Type


object CacheService {

    fun getFood(id: String): FoodResponseModel? {
        val cache = get("food-$id", object: TypeToken<CacheItem<FoodResponseModel>>(){}.type, FoodResponseModel::class.java)
        cache ?: return null
        return cache.data
    }

    fun putFood(model: FoodResponseModel) {
        put("food-${model.id}", model, 1000 * 60 * 60 * 24)
    }

    fun <T>get(key: String, typeToken: Type, c: Class<T>): CacheItem<T>? {
        val data = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext).getString(key, "")

        if (!data.isNullOrEmpty()) {
            return Gson().fromJson(data, typeToken)
        }

        return null
    }

    fun <T>put(key: String, data: T, cacheLength: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext)
        val cacheItem = CacheItem(data, cacheLength)
        val collectionType = object: TypeToken<CacheItem<T>>(){}.type
        pref.edit().putString(key, Gson().toJson(cacheItem, collectionType)).apply()
    }

}
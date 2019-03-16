package com.twobrothers.overcooked.app

import android.preference.PreferenceManager
import com.google.gson.Gson
import com.twobrothers.overcooked.Overcooked
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import com.twobrothers.overcooked.utils.CacheItem
import java.lang.reflect.Type


object CacheService {

    fun get(key: String):CacheItem? {
        val data = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext).getString(key, "")

        if (!data.isNullOrEmpty()) {
            return Gson().fromJson(data, CacheItem::class.java)
        }

        return null
    }

    fun put(key: String, data: Any, cacheLength: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext)
        val cacheItem = CacheItem(data, cacheLength)
        pref.edit().putString(key, Gson().toJson(cacheItem)).apply()
    }

}
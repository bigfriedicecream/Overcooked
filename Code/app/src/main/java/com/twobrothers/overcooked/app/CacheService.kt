package com.twobrothers.overcooked.app

import android.preference.PreferenceManager
import com.google.gson.Gson
import com.twobrothers.overcooked.Overcooked
import com.twobrothers.overcooked.utils.CacheItem
import java.lang.reflect.Type


object CacheService {

    fun <T>get(key:String, type:Type, model:T):CacheItem<T>? {
        val data = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext).getString(key, "")

        if (!data.isNullOrEmpty()) {
            return Gson().fromJson(data, type)
        }

        return null
    }

    fun <T>put(key:String, data:T) {
        val pref = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext)
        val cacheItem = CacheItem(data, System.currentTimeMillis() + 1000 * 60 * 2)
        pref.edit().putString(key, Gson().toJson(cacheItem)).apply()
    }

}
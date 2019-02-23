package com.twobrothers.overcooked.app

import android.preference.PreferenceManager
import com.google.gson.Gson
import com.twobrothers.overcooked.Overcooked

object CacheService {

    fun <T>get(key:String, cast:Class<T>):T? {
        val data = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext).getString(key, "")

        if (!data.isNullOrEmpty()) {
            return Gson().fromJson(data, cast)
        }

        return null
    }

    fun put(key:String, data:String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext)
        pref.edit().putString(key, data).apply()
    }

}
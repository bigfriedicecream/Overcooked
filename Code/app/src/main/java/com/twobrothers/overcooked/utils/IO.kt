package com.twobrothers.overcooked.utils

import android.preference.PreferenceManager
import com.twobrothers.overcooked.Overcooked

class IO {
    companion object {
        fun getInt(name:String):Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext)
            return preferences.getInt(name, -1)
        }
        fun putInt(name:String, value:Int) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext).edit()
            preferences.putInt(name, value)
            preferences.apply()
        }
    }
}
package com.twobrothers.overcooked

import android.app.Application
import android.content.Context

class Overcooked:Application() {

    companion object {
        var appContext:Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        Overcooked.appContext = applicationContext
    }
}

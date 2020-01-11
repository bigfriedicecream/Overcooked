package com.twobrothers.overcooked.core

import android.app.Application
import android.content.Context


class App : Application() {

    // TODO: Inject context where required
    companion object {
        private lateinit var context: Context

        fun getAppContext(): Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


}
package com.twobrothers.overcooked.interfaces

import android.content.Context

interface IMainContract {

    interface View {
        fun renderList()
        fun renderRecipe(id: String)
    }

    interface Presenter {
        fun start(c:Context)
        fun stop(c:Context)
        fun load()
    }
}

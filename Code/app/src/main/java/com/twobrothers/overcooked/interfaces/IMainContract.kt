package com.twobrothers.overcooked.interfaces


interface IMainContract {

    interface View {
        fun renderList()
        fun renderRecipe(id:String)
    }

    interface Presenter {
        fun start()
        fun stop()
        fun load()
    }
}

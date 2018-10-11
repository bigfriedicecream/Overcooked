package com.bigfriedicecream.overcooked.interfaces


interface IRecipeContract {

    interface View {
        fun render()
    }

    interface Presenter {
        fun start()
        fun stop()
        fun load(id:String)
    }
}

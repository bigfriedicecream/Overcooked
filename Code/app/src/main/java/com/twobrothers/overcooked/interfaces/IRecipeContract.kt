package com.twobrothers.overcooked.interfaces


interface IRecipeContract {

    interface View {
        fun render()
    }

    interface Presenter {
        fun onStart()
        fun onStop()
    }

}

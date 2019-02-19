package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.RecipeModel


interface IRecipeListContract {

    interface View {

    }

    interface Presenter {
        fun onStart()
        fun onStop()
    }
}

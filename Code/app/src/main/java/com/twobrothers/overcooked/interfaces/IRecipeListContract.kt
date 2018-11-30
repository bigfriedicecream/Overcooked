package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.RecipeModel


interface IRecipeListContract {

    interface View {
        fun render(recipeList:HashMap<String, RecipeModel>)
    }

    interface Presenter {
        fun start()
        fun stop()
    }
}

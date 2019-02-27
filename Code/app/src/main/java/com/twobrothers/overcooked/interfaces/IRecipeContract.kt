package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.recipe.RecipeModel


interface IRecipeContract {

    interface View {
        fun render(recipe: RecipeModel)
    }

    interface Presenter {
        fun onStart()
        fun onStop()
    }

}

package com.twobrothers.overcooked.interfaces

import android.content.Context
import com.twobrothers.overcooked.models.RecipeModel


interface IRecipeListContract {

    interface View {
        fun render(recipeList:HashMap<String, RecipeModel>)
    }

    interface Presenter {
        fun start(c:Context)
        fun stop()
    }
}

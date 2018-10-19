package com.bigfriedicecream.overcooked.interfaces

import android.content.Context
import com.bigfriedicecream.overcooked.models.RecipeModel


interface IRecipeListContract {

    interface View {
        fun render(recipeList:HashMap<String, RecipeModel>)
    }

    interface Presenter {
        fun start(c:Context)
        fun stop()
    }
}

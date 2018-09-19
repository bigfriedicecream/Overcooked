package com.bigfriedicecream.recipes.interfaces

import android.content.Context

import com.bigfriedicecream.recipes.models.RecipeDataModel

interface IRecipeListContract {

    interface View {
        fun render(recipeList: Map<String, RecipeDataModel>)
    }

    interface Presenter {
        fun start(c:Context)
        fun stop()
    }
}

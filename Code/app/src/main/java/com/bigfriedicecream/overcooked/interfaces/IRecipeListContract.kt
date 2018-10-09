package com.bigfriedicecream.overcooked.interfaces

import android.content.Context

import com.bigfriedicecream.overcooked.models.RecipeDataModel

interface IRecipeListContract {

    interface View {
        fun render(recipeList:Map<String, RecipeDataModel>?)
    }

    interface Presenter {
        fun start(c:Context)
        fun stop()
    }
}

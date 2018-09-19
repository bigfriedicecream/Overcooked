package com.bigfriedicecream.recipes.interfaces

import com.bigfriedicecream.recipes.models.RecipeDataModel

interface IRecipeContract {

    interface View {
        fun render(recipe: RecipeDataModel)
    }

    interface Presenter {
        fun start()
        fun stop()
        fun load(id:String)
    }
}

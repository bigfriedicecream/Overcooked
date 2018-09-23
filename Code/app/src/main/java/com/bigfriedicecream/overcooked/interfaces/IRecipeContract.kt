package com.bigfriedicecream.overcooked.interfaces

import com.bigfriedicecream.overcooked.models.RecipeDataModel

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

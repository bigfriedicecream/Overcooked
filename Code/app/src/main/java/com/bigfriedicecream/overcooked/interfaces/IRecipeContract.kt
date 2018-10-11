package com.bigfriedicecream.overcooked.interfaces

import com.bigfriedicecream.overcooked.models.RecipeModel


interface IRecipeContract {

    interface View {
        fun render(recipe:RecipeModel)
    }

    interface Presenter {
        fun start(id:String?)
        fun stop()
    }
}

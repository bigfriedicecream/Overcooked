package com.bigfriedicecream.overcooked.interfaces

import android.content.Context
import com.bigfriedicecream.overcooked.models.RecipeModel


interface IRecipeContract {

    interface View {
        fun render(recipe:RecipeModel)
    }

    interface Presenter {
        fun start(context:Context?, id:String?)
        fun stop()
    }
}

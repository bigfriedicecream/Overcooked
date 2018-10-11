package com.bigfriedicecream.overcooked.interfaces

import android.content.Context
import com.bigfriedicecream.overcooked.models.CondensedRecipeModel


interface IRecipeListContract {

    interface View {
        fun render(recipeList:List<CondensedRecipeModel>)
    }

    interface Presenter {
        fun start(c:Context)
        fun stop()
    }
}

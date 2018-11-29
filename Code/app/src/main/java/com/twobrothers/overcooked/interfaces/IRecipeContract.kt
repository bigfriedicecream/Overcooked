package com.twobrothers.overcooked.interfaces

import android.content.Context
import com.twobrothers.overcooked.models.RecipeModel


interface IRecipeContract {

    interface View {
        fun render(recipe:RecipeModel?)
        fun showAdjustQuantityDialog(recipe:RecipeModel?)
    }

    interface Presenter {
        fun start(context:Context?, id:String?, restore:Boolean? = false)
        fun stop()
        fun adjustQuantityClick()
        fun updateQuantity(newQuantity:Int, context:Context?)
    }
}

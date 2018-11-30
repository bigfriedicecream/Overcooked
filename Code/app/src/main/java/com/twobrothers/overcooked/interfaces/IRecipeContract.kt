package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.RecipeModel


interface IRecipeContract {

    interface View {
        fun render(recipe:RecipeModel?)
        fun showAdjustQuantityDialog(recipe:RecipeModel?)
    }

    interface Presenter {
        fun start(id:String?, restore:Boolean? = false)
        fun stop()
        fun adjustQuantityClick()
        fun updateQuantity(newQuantity:Int)
    }
}

package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeModel

object AppState {
    object Recipe {
        var data: RecipeModel? = null
            set (it) {
                field = it
                activeQuantity = it?.serves ?: it?.makes ?: 0
            }
        var activeQuantity: Int = 0
    }
}
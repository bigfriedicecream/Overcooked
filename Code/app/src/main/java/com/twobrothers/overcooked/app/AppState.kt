package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeModel

object AppState {
    object Recipe {
        var data: RecipeModel? = null
        var activeQuantity: Int = 0
    }
}
package com.twobrothers.overcooked.models.recipelist

import com.twobrothers.overcooked.models.recipe.RecipeModel

class RecipeListModel {
    val data: Data = Data()
    class Data {
        val recipes:ArrayList<RecipeModel> = ArrayList()
    }
}
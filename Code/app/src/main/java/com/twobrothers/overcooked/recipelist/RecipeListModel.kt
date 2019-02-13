package com.twobrothers.overcooked.recipelist

import com.twobrothers.overcooked.recipe.RecipeModel

class RecipeListModel {
    val data:Data = Data()
    class Data {
        val recipes:ArrayList<RecipeModel> = ArrayList()
        val last_page:Boolean = false
    }
}
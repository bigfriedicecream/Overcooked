package com.twobrothers.overcooked.models.recipe

class RecipeDataModel {
    val data: Data = Data()
    class Data {
        val recipe: RecipeModel = RecipeModel()
    }
}
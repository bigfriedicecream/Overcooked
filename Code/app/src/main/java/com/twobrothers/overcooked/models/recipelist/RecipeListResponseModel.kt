package com.twobrothers.overcooked.models.recipelist

import com.twobrothers.overcooked.models.food.FoodResponseModel
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel

class RecipeListResponseModel {
    val data: Data = Data()
    class Data {
        val recipes:ArrayList<RecipeResponseModel.Recipe> = ArrayList()
        val food: HashMap<String, FoodResponseModel> =  HashMap()
    }
}
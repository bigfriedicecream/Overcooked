package com.bigfriedicecream.overcooked.models

import com.google.gson.JsonObject
import java.util.HashMap

class RecipeResponseDataModel {
    var recipes:HashMap<String, RecipeDataModel>? = null
    class RecipeDataModel {
        var id:String = ""
        var title:String = ""
        var serves:Int = 0
        var makes:Int = 0
        var prepTime:Int = 0
        var cookTime:Int = 0
        var ings:MutableList<JsonObject> = mutableListOf()
        var method:List<String> = ArrayList()
    }
}

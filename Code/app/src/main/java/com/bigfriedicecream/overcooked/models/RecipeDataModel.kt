package com.bigfriedicecream.overcooked.models

class RecipeDataModel {
    var id:String = ""
    var title:String = ""
    var serves:Int = 0
    var makes:Int = 0
    var prepTime:Int = 0
    var cookTime:Int = 0
    var ingredients:List<String>? = null
    var method:List<String>? = null
}

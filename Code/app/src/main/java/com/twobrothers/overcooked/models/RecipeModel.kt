package com.twobrothers.overcooked.models

class RecipeModel {
    var id:String = ""
    var title:String = ""
    var imageURL:String = ""
    var serves:Int = 0
    var makes:Int = 0
    var prepTime:Int = 0
    var cookTime:Int = 0
    var multiplier:Double = 1.0
    var ingredients:MutableList<Any?> = ArrayList()
    var method:List<String> = ArrayList()
}
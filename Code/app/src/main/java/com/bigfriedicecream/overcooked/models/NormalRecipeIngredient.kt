package com.bigfriedicecream.overcooked.models

class NormalRecipeIngredient:BaseRecipeIngredient() {
    var quantity:Double = 0.0
    var ingredientId:String = ""
    var ingUnitTypeId:Int = 0
    var alternateUnit:Int = 0
    var name:String = ""
    var namePlural:String = ""
    var unitTypes:List<UnitData> = ArrayList()
}
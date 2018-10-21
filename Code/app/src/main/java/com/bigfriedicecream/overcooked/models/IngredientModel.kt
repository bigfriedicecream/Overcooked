package com.bigfriedicecream.overcooked.models

import com.bigfriedicecream.overcooked.lookups.LookupIngUnitType

open class IngredientModel {
    var id:String = ""
    var name:String = ""
    var namePlural:String = ""
    var ingUnitTypeId:Int = LookupIngUnitType.Singular.id
}
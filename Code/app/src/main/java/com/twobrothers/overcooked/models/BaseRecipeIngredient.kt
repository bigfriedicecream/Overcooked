package com.twobrothers.overcooked.models

import com.twobrothers.overcooked.lookups.LookupIngDisplayType

open class BaseRecipeIngredient {
    var ingDisplayTypeId:Int = LookupIngDisplayType.Normal.id
}
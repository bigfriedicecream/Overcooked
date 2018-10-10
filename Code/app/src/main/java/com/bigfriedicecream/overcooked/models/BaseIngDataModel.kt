package com.bigfriedicecream.overcooked.models

import com.bigfriedicecream.overcooked.lookups.LookupIngDisplayType

open class BaseIngDataModel {
    var ingDisplayTypeId:Int = LookupIngDisplayType.Normal.id
}
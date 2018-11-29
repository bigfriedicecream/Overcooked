package com.twobrothers.overcooked.lookups

import java.util.*

enum class LookupIngDisplayType constructor(val id:Int, val description:String) {
    Normal(0, "Normal"),
    Heading(1, "Heading"),
    TextOnly(2, "Text Only");

    fun dataLookup(id:Int):LookupIngDisplayType? {
        when (id) {
            LookupIngDisplayType.Normal.id -> return LookupIngDisplayType.Normal
            LookupIngDisplayType.Heading.id -> return LookupIngDisplayType.Heading
            LookupIngDisplayType.TextOnly.id -> return LookupIngDisplayType.TextOnly
        }
        return null
    }

    fun dataList():List<LookupIngDisplayType> {
        return ArrayList(EnumSet.allOf(LookupIngDisplayType::class.java))
    }
}
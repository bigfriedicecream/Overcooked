package com.bigfriedicecream.overcooked.lookups

import java.util.*

enum class LookupIngUnitType constructor(val id:Int, val description:String, shortName:String, shortNamePlural:String) {
    Singular(0, "Singular", " x ", " x "),
    Grams(1, "Grams", "g ", "g "),
    Tsp(2, "Tsp", " tsp ", " tsp "),
    Tbs(2, "Tbs", " tbs ", " tbs "),
    Cups(2, "Cups", " cup", " cups "),
    Bunch(2, "Bunch", " bunch of ", " bunches of "),
    Rashers(2, "Rashers", " rasher of ", " rashes of "),
    Head(2, "Head", " head of ", " heads of "),
    Sprig(2, "Sprig", " sprig of ", " sprigs of "),
    Stalk(2, "Stalk", " stalk of ", " stalks of "),
    Sheets(2, "Sheets", " sheet of ", " sheets of "),
    Slice(2, "Slice", " slice of ", " slices of ");

    fun dataLookup(id:Int):LookupIngUnitType? {
        when (id) {
            LookupIngUnitType.Singular.id -> return LookupIngUnitType.Singular
            LookupIngUnitType.Grams.id -> return LookupIngUnitType.Grams
            LookupIngUnitType.Tsp.id -> return LookupIngUnitType.Tsp
            LookupIngUnitType.Tbs.id -> return LookupIngUnitType.Tbs
            LookupIngUnitType.Cups.id -> return LookupIngUnitType.Cups
            LookupIngUnitType.Bunch.id -> return LookupIngUnitType.Bunch
            LookupIngUnitType.Rashers.id -> return LookupIngUnitType.Rashers
            LookupIngUnitType.Head.id -> return LookupIngUnitType.Head
            LookupIngUnitType.Sprig.id -> return LookupIngUnitType.Sprig
            LookupIngUnitType.Stalk.id -> return LookupIngUnitType.Stalk
            LookupIngUnitType.Sheets.id -> return LookupIngUnitType.Sheets
            LookupIngUnitType.Slice.id -> return LookupIngUnitType.Slice
        }
        return null
    }

    fun dataList():List<LookupIngUnitType> {
        return ArrayList(EnumSet.allOf(LookupIngUnitType::class.java))
    }
}
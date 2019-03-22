package com.twobrothers.overcooked.lookups

enum class LookupIngredientUnitType constructor(val id: Int, val singular: String, val plural: String) {
    None(-1, "", ""),
    Singular(0, " ", " "),
    Grams(1, "g ", "g "),
    Millilitres(2, "ml ", "ml "),
    Tsp(3, " tsp ", " tsp "),
    Tbsp(4, " Tbsp ", " Tbsp "),
    Cups(5, " cup ", " cups "),
    Bunch(6, " bunch of ", " bunches of "),
    Rasher(7, " rasher of ", " rashes of "),
    Head(8, " head of ", " heads of "),
    Sprig(9, " sprig of ", " sprigs of "),
    Stalk(10, " stalk of ", " stalks of "),
    Sheet(11, " sheet of ", " sheets of "),
    Slice(12, " slice of ", " slices of "),
    Kilograms(101, "kg ", "kg "),
    Litres(201, "L ", "L ");

    companion object {
        fun dataLookup(id: Int): LookupIngredientUnitType {
            when (id) {
                Singular.id -> return Singular
                Grams.id -> return Grams
                Millilitres.id -> return Millilitres
                Tsp.id -> return Tsp
                Tbsp.id -> return Tbsp
                Cups.id -> return Cups
                Bunch.id -> return Bunch
                Rasher.id -> return Rasher
                Head.id -> return Head
                Sprig.id -> return Sprig
                Stalk.id -> return Stalk
                Sheet.id -> return Sheet
                Slice.id -> return Slice
                Kilograms.id -> return Kilograms
                Litres.id -> return Litres
            }
            return None
        }
    }
}
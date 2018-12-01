package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.lookups.LookupIngUnitType
import com.twobrothers.overcooked.models.NormalRecipeIngredient
import com.twobrothers.overcooked.utils.fractionFromNumber
import com.twobrothers.overcooked.utils.fromHtml
import com.twobrothers.overcooked.utils.roundToReadableQuantity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_textonly_ingredient_item.view.*


class NormalIngredientItemFragment:Fragment() {

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View? {
        val view = inflater.inflate(R.layout.fragment_textonly_ingredient_item, container, false)

        val data:String? = arguments?.getString("data")
        var multiplier:Double? = arguments?.getDouble("multiplier")

        if (multiplier == null) {
            multiplier = 1.0
        }

        if (data != null) {
            // normal unit type
            val ing:NormalRecipeIngredient = Gson().fromJson(data, NormalRecipeIngredient::class.java)
            val unitType = LookupIngUnitType.dataLookup(ing.ingUnitTypeId)
            val quantity = Double.roundToReadableQuantity(ing.quantity * multiplier, ing.ingUnitTypeId)
            val quantityAsFraction = Double.fractionFromNumber(quantity)
            val unitName = if (quantity > 1) unitType?.shortNamePlural else unitType?.shortName
            val name = if (quantity > 1) String.fromHtml(ing.namePlural) else String.fromHtml(ing.name)

            // alternate unit type
            var alternateDisplay = ""
            if (ing.alternateUnit != 0) {
                val altUnitType = LookupIngUnitType.dataLookup(ing.alternateUnit)
                val altUnitData = ing.unitTypes.find { unitData -> unitData.id == ing.alternateUnit }
                val altQuantity = if (altUnitData != null) Double.roundToReadableQuantity(altUnitData.ratio * ing.quantity * multiplier, ing.alternateUnit) else 0.0
                val altQuantityAsFraction = Double.fractionFromNumber(altQuantity)
                val altUnitName = if (altQuantity > 1) altUnitType?.shortNamePlural?.trimEnd() else altUnitType?.shortName?.trimEnd()
                alternateDisplay = "($altQuantityAsFraction$altUnitName) "
            }

            // end description
            val endDesc = if (ing.endDesc.isNotEmpty()) ", ${String.fromHtml(ing.endDesc)}" else ""

            view.item.text = "$quantityAsFraction$unitName$alternateDisplay$name$endDesc"
        }

        return view
    }
}

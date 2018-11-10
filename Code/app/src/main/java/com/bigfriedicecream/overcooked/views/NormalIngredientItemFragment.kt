package com.bigfriedicecream.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bigfriedicecream.overcooked.R
import com.bigfriedicecream.overcooked.lookups.LookupIngUnitType
import com.bigfriedicecream.overcooked.models.NormalRecipeIngredient
import com.bigfriedicecream.overcooked.models.UnitData
import com.bigfriedicecream.overcooked.utils.fractionFromNumber
import com.bigfriedicecream.overcooked.utils.fromHtml
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_textonly_ingredient_item.view.*


class NormalIngredientItemFragment:Fragment() {

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View? {
        val view:View = inflater.inflate(R.layout.fragment_textonly_ingredient_item, container, false)

        val data:String? = arguments?.getString("data")
        var multiplier:Double? = arguments?.getDouble("multiplier")
        if (multiplier == null) {
            multiplier = 1.0
        }

        if (data != null) {
            val ing:NormalRecipeIngredient = Gson().fromJson(data, NormalRecipeIngredient::class.java)

            // primary unit
            val primaryUnitType = LookupIngUnitType.dataLookup(ing.ingUnitTypeId)
            val primaryQuantity = Double.fractionFromNumber(ing.quantity * multiplier)
            val primaryUnit = if (ing.quantity > 1) primaryUnitType?.shortNamePlural else primaryUnitType?.shortName

            // alternate unit
            var alternateString = ""
            if (ing.alternateUnit != 0) {
                val alternateUnitType = LookupIngUnitType.dataLookup(ing.alternateUnit)
                val alternateUnitData = ing.unitTypes.find { unitData -> unitData.id == ing.alternateUnit }
                val alternateQuantity = if (alternateUnitData != null) alternateUnitData.ratio * ing.quantity * multiplier else 0.0
                val alternateQuantityString = Double.fractionFromNumber(alternateQuantity)
                val alternateIngName = if (alternateQuantity > 1) alternateUnitType?.shortNamePlural?.trimEnd() else alternateUnitType?.shortName?.trimEnd()
                alternateString = "($alternateQuantityString$alternateIngName) "
            }

            val ingName = if (ing.quantity > 1) String.fromHtml(ing.namePlural) else String.fromHtml(ing.name)
            val endDesc = if (ing.endDesc.isNotEmpty()) ", ${String.fromHtml(ing.endDesc)}" else ""

            val displayResult = "$primaryQuantity$primaryUnit$alternateString$ingName$endDesc"

            view.item.text = displayResult
        }

        return view
    }
}

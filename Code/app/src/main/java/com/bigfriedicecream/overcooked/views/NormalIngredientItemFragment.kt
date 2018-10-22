package com.bigfriedicecream.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bigfriedicecream.overcooked.R
import com.bigfriedicecream.overcooked.lookups.LookupIngUnitType
import com.bigfriedicecream.overcooked.models.NormalRecipeIngredient
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_textonly_ingredient_item.view.*


class NormalIngredientItemFragment:Fragment() {

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View? {
        val view:View = inflater.inflate(R.layout.fragment_textonly_ingredient_item, container, false)

        val data:String? = arguments?.getString("data")

        if (data != null) {
            val ing:NormalRecipeIngredient = Gson().fromJson(data, NormalRecipeIngredient::class.java)

            val ingUnitType:LookupIngUnitType? = LookupIngUnitType.dataLookup(ing.ingUnitTypeId)
            val name:String = if (ing.quantity > 1) ing.namePlural else ing.name
            val unit:String? = if (ing.quantity > 1) ingUnitType?.shortNamePlural else ingUnitType?.shortName

            val displayResult = "${ing.quantity}$unit$name"

            view.item.text = displayResult
        }

        return view
    }
}

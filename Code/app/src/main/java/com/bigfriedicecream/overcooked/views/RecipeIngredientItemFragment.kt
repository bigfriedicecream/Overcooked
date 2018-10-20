package com.bigfriedicecream.overcooked.views

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bigfriedicecream.overcooked.R
import com.bigfriedicecream.overcooked.lookups.LookupIngDisplayType
import com.bigfriedicecream.overcooked.models.HeadingRecipeIngredient
import com.bigfriedicecream.overcooked.models.TextOnlyRecipeIngredient
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_recipe_ingredient_item.view.*
import com.google.gson.JsonParser
import android.support.v4.content.res.ResourcesCompat
import android.util.TypedValue


class RecipeIngredientItemFragment:Fragment() {

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View? {
        val view:View = inflater.inflate(R.layout.fragment_recipe_ingredient_item, container, false)

        if (arguments != null) {
            val item:String? = arguments?.getString("item")
            val obj:JsonObject = JsonParser().parse(item).asJsonObject

            when (obj.get("ingDisplayTypeId").asInt) {
                LookupIngDisplayType.Normal.id -> {
                    //println("normal")
                }
                LookupIngDisplayType.Heading.id -> {
                    val model:HeadingRecipeIngredient = GsonBuilder().create().fromJson<HeadingRecipeIngredient>(item, HeadingRecipeIngredient::class.java)
                    view.item.typeface = ResourcesCompat.getFont(context!!, R.font.mission_gothic_regular)
                    view.item.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                    view.item.setTextColor(Color.parseColor("#3e3e3e"))
                    view.item.text = model.display.toUpperCase()
                }
                LookupIngDisplayType.TextOnly.id -> {
                    val model:TextOnlyRecipeIngredient = GsonBuilder().create().fromJson<TextOnlyRecipeIngredient>(item, TextOnlyRecipeIngredient::class.java)
                    view.item.text = model.display
                }
            }
        }

        return view
    }
}

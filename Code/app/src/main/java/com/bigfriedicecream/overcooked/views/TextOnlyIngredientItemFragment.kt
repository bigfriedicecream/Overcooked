package com.bigfriedicecream.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bigfriedicecream.overcooked.R
import com.bigfriedicecream.overcooked.models.TextOnlyRecipeIngredient
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_textonly_ingredient_item.view.*


class TextOnlyIngredientItemFragment:Fragment() {

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View? {
        val view:View = inflater.inflate(R.layout.fragment_textonly_ingredient_item, container, false)

        val data:String? = arguments?.getString("data")

        if (data != null) {
            val recipeIngredient:TextOnlyRecipeIngredient = Gson().fromJson(data, TextOnlyRecipeIngredient::class.java)
            view.item.text = recipeIngredient.display
        }

        /* if (arguments != null) {
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
        }*/

        return view
    }
}

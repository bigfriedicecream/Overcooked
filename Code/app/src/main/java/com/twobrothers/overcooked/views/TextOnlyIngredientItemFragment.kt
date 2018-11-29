package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.models.TextOnlyRecipeIngredient
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

        return view
    }
}

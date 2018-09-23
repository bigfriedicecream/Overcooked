package com.bigfriedicecream.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bigfriedicecream.overcooked.R
import kotlinx.android.synthetic.main.fragment_recipe_ingredient_item.view.*

class RecipeIngredientItemFragment:Fragment() {

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View? {
        val view:View = inflater.inflate(R.layout.fragment_recipe_ingredient_item, container, false)

        if (arguments != null) {
            view.item.text = arguments!!.getString("item")
        }

        return view
    }
}

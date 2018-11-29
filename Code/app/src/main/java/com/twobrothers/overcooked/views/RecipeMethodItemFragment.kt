package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.utils.fromHtml
import kotlinx.android.synthetic.main.fragment_recipe_method_item.view.*

class RecipeMethodItemFragment:Fragment() {

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View? {
        val view:View = inflater.inflate(R.layout.fragment_recipe_method_item, container, false)

        if (arguments != null) {
            view.counter.text = arguments!!.getInt("counter").toString()
            val step:String = arguments!!.getString("step")
            view.step.text = String.fromHtml(step)
        }

        return view
    }
}

package com.bigfriedicecream.recipes.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bigfriedicecream.recipes.BuildConfig
import com.bigfriedicecream.recipes.R
import com.bigfriedicecream.recipes.models.RecipeDataModel
import com.bigfriedicecream.recipes.observables.EventsDispatcher
import com.bigfriedicecream.recipes.utils.GlideApp
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

import kotlinx.android.synthetic.main.fragment_recipe_list_item.view.*

class RecipeListItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_recipe_list_item, container, false)

        if (arguments != null) {
            val model:RecipeDataModel = Gson().fromJson(arguments?.getString("data"), RecipeDataModel::class.java)

            view.title.text = model.title

            GlideApp
                    .with(view)
                    .load("${BuildConfig.BASE_URL}/Assets/images/${model.id}/hero.jpg")
                    .fitCenter()
                    .into(view.hero)

            val eventsDispatcher:EventsDispatcher = EventsDispatcher.getInstance()

            view.setOnClickListener { eventsDispatcher.dispatch("NAVIGATION_RECIPE", model.id) }
        }

        return view
    }
}

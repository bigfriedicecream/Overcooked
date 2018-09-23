package com.bigfriedicecream.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bigfriedicecream.overcooked.BuildConfig
import com.bigfriedicecream.overcooked.R
import com.bigfriedicecream.overcooked.models.RecipeDataModel
import com.bigfriedicecream.overcooked.observables.EventsDispatcher
import com.bigfriedicecream.overcooked.utils.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(view.hero)

            val eventsDispatcher:EventsDispatcher = EventsDispatcher.getInstance()

            view.setOnClickListener { eventsDispatcher.dispatch("NAVIGATION_RECIPE", model.id) }
        }

        return view
    }
}

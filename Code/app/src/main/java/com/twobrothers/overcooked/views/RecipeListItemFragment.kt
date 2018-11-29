package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.models.RecipeModel
import com.twobrothers.overcooked.observables.EventsDispatcher
import com.twobrothers.overcooked.utils.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson

import kotlinx.android.synthetic.main.fragment_recipe_list_item.view.*

class RecipeListItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_recipe_list_item, container, false)

        if (arguments != null) {
            val recipe:RecipeModel = Gson().fromJson(arguments?.getString("data"), RecipeModel::class.java)
            view.title.text = recipe.title

            GlideApp
                    .with(view)
                    .load(recipe.imageURL)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(view.hero)

            val eventsDispatcher:EventsDispatcher = EventsDispatcher.instance

            view.setOnClickListener { eventsDispatcher.dispatch("NAVIGATION_RECIPE", recipe.id) }
        }

        return view
    }
}

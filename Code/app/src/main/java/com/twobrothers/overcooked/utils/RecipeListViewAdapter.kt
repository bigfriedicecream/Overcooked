package com.twobrothers.overcooked.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.RepoRowView
import com.twobrothers.overcooked.models.recipe.RecipeModel
import kotlinx.android.synthetic.main.fragment_recipe_list_item.view.*

class RecipeListViewAdapter(private val presenter:IRecipeListContract.Presenter, private val dataSet:MutableList<RecipeModel>):RecyclerView.Adapter<TheViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheViewHolder {
        val layout:RelativeLayout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recipe_list_item, parent, false) as RelativeLayout
        return TheViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        presenter.onBindRepositoryRowViewAtPosition(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getRepositoriesRowsCount()
    }
}
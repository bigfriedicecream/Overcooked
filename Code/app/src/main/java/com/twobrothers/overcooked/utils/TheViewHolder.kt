package com.twobrothers.overcooked.utils

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.RepoRowView
import com.twobrothers.overcooked.models.recipe.RecipeModel
import kotlinx.android.synthetic.main.fragment_recipe_list_item.view.*

class TheViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), RepoRowView {
    override fun renderino(recipe: RecipeModel) {
        itemView.title.text = recipe.title
        GlideApp
                .with(itemView.context)
                .load(recipe.imageUrl)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(itemView.hero)
    }
}
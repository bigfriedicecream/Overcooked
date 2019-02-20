package com.twobrothers.overcooked.views.recipelist

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeListRowView
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.utils.GlideApp
import kotlinx.android.synthetic.main.fragment_recipe_list_item.view.*

class RecipeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), IRecipeListRowView {
    override fun render(recipe: RecipeModel) {
        itemView.title.text = recipe.title
        GlideApp.with(itemView.context)
                .load(recipe.imageUrl)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(itemView.hero)
    }
}
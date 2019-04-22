package com.twobrothers.overcooked.views.recipelist

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.jakewharton.rxbinding.view.RxView
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.IRecipeListRowView
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.utils.GlideApp
import kotlinx.android.synthetic.main.fragment_recipe_list_item.view.*

class RecipeListViewHolder(itemView: View, private val presenter:IRecipeListContract.Presenter) : RecyclerView.ViewHolder(itemView), IRecipeListRowView {
    override fun render(recipe: RecipeResponseModel.Recipe) {
        itemView.text_title.text = recipe.title
        GlideApp.with(itemView.context)
                .load("${BuildConfig.IMAGE_ENDPOINT}${recipe.imageUrl}")
                .signature(ObjectKey("${recipe.id}${recipe.lastUpdated}"))
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(itemView.image_hero)

        RxView.clicks(itemView)
                // .takeUntil(RxView.detaches(itemView))
                .subscribe {
                    presenter.onRecipeListItemClick(adapterPosition)
                }
    }
}
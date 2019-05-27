package com.twobrothers.overcooked.views.recipelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.jakewharton.rxbinding.view.RxView
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.IRecipeListViewHolder
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.utils.GlideApp
import kotlinx.android.synthetic.main.fragment_recipe_list_item.view.*

class RecipeListViewAdapter(private val presenter: IRecipeListContract.Presenter): RecyclerView.Adapter<RecipeListViewAdapter.Holder>() {

    class Holder(itemView: View, private val presenter:IRecipeListContract.Presenter): RecyclerView.ViewHolder(itemView), IRecipeListViewHolder {
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
                    .subscribe {
                        presenter.onRecipeListItemClick(adapterPosition)
                    }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recipe_list_item, parent, false) as RelativeLayout
        return Holder(layout, presenter)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        presenter.onBindRepositoryRowViewAtPosition(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getRepositoriesRowsCount()
    }
}
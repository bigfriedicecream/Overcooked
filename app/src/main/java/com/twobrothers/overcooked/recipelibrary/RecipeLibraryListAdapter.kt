package com.twobrothers.overcooked.recipelibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.recipelibrary.models.RecipeSummary


class RecipeLibraryListAdapter(
    private val onRecipeClickListener: OnRecipeClickListener
) :
    ListAdapter<RecipeSummary, RecipeLibraryListAdapter.RecipeListViewHolder>(RecipeListDifferConfig()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_recipe_list_item, parent, false)
        return RecipeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RecipeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val root = itemView.findViewById<View>(R.id.card_root)
        private val heroImage = itemView.findViewById<ImageView>(R.id.image_hero)
        private val title = itemView.findViewById<TextView>(R.id.text_title)

        fun bind(recipe: RecipeSummary) {
            title.text = recipe.title
            Glide
                .with(root)
                .load(recipe.heroImageUrl)
                .centerCrop()
                .placeholder(R.drawable.img_placeholder_16_9)
                .into(heroImage)
            root.setOnClickListener {
                onRecipeClickListener.onClick(recipe.id)
            }
        }
    }

    private class RecipeListDifferConfig : DiffUtil.ItemCallback<RecipeSummary>() {
        override fun areItemsTheSame(oldItem: RecipeSummary, newItem: RecipeSummary): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RecipeSummary,
            newItem: RecipeSummary
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

}

interface OnRecipeClickListener {
    fun onClick(id: String)
}
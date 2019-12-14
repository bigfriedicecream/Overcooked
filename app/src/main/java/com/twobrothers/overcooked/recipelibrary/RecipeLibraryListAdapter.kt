package com.twobrothers.overcooked.recipelibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.recipedetails.models.Recipe


class RecipeLibraryListAdapter(
    private val onRecipeClickListener: OnRecipeClickListener
) :
    ListAdapter<Recipe, RecipeLibraryListAdapter.RecipeListViewHolder>(RecipeListDifferConfig()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_recipe_list_item, parent, false)
        return RecipeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RecipeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe) {
            itemView.findViewById<TextView>(R.id.text_title).text = recipe.title
            itemView.setOnClickListener {
                onRecipeClickListener.onClick(recipe.id)
            }
        }
    }

    private class RecipeListDifferConfig : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Recipe,
            newItem: Recipe
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

}

interface OnRecipeClickListener {
    fun onClick(id: String)
}
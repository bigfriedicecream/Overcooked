package com.twobrothers.overcooked.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.twobrothers.overcooked.R
import kotlinx.android.synthetic.main.fragment_recipe_list_item.view.*

class RecipeListViewAdapter(private val dataSet:Array<String>):RecyclerView.Adapter<RecipeListViewAdapter.ViewHolder>() {

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout:RelativeLayout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recipe_list_item, parent, false) as RelativeLayout
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.title.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
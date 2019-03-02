package com.twobrothers.overcooked.views.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.twobrothers.overcooked.Overcooked
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.models.recipe.RecipeModel
import kotlinx.android.synthetic.main.fragment_recipe_ingredient_child.view.*


class IngredientListAdapter(private val context: Context?, private val dataSet: ArrayList<RecipeModel.IngredientSection>): BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int): String {
        println(groupPosition)
        return "heading"
        // return dataSet[groupPosition].heading
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.fragment_recipe_ingredient_group, null)
        }
        val title = convertView?.findViewById<TextView>(R.id.text_title)
        title?.text = getGroup(groupPosition)
        return convertView
    }

    override fun getChildrenCount(index: Int): Int {
        return dataSet[index].ingredients.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        // return dataSet[groupPosition].ingredients[childPosition]
        return "this is a child"
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.fragment_recipe_ingredient_child, null)
        }
        val title = convertView?.findViewById<TextView>(R.id.text_title)
        title?.text = getChild(groupPosition, childPosition)
        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return dataSet.size
    }
}
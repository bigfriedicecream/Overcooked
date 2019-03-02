package com.twobrothers.overcooked.views.recipe

import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.twobrothers.overcooked.models.recipe.RecipeModel

class IngredientListAdapter(private val dataSet: ArrayList<RecipeModel.IngredientSection>): BaseExpandableListAdapter() {
    override fun getGroup(index: Int): String {
        return dataSet[index].heading
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChildrenCount(index: Int): Int {
        return dataSet[index].ingredients.size
    }

    override fun getChild(groupIndex: Int, childIndex: Int): Any {
        return dataSet[groupIndex].ingredients[childIndex]
    }

    override fun getGroupId(index: Int): Long {
        return index.toLong()
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return dataSet.size
    }
}
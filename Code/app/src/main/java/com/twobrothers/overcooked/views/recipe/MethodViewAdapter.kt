package com.twobrothers.overcooked.views.recipe

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeContract
import kotlinx.android.synthetic.main.fragment_recipe_method_item.view.*

class MethodViewAdapter(private val presenter: IRecipeContract.Presenter):RecyclerView.Adapter<MethodViewAdapter.Holder>() {

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun render(step: Int, description: String) {
            itemView.text_step.text = step.toString()
            itemView.text_description.text = description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recipe_method_item, parent, false)
        return Holder(layout)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        presenter.onBindMethodRepositoryRowViewAtPosition(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getMethodRepositoriesRowsCount()
    }
}
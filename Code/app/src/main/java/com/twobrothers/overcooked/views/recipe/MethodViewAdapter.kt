package com.twobrothers.overcooked.views.recipe

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.jakewharton.rxbinding.view.RxView
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.presenters.RecipePresenter
import kotlinx.android.synthetic.main.fragment_recipe_method_item.view.*

class MethodViewAdapter(private val presenter: IRecipeContract.Presenter):RecyclerView.Adapter<MethodViewAdapter.Holder>() {

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun render(step: Int, description: String) {
            itemView.text_step.text = step.toString()
            itemView.text_description.text = description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MethodViewAdapter.Holder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recipe_method_item, parent, false)
        return MethodViewAdapter.Holder(layout)
    }

    override fun onBindViewHolder(holder: MethodViewAdapter.Holder, position: Int) {
        presenter.onBindMethodRepositoryRowViewAtPosition(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getMethodRepositoriesRowsCount()
    }
}
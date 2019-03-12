package com.twobrothers.overcooked.views.recipe

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.models.recipe.RecipeModel
import kotlinx.android.synthetic.main.fragment_recipe_ingredient_quantified.view.*

class IngredientViewAdapter(private val presenter: IRecipeContract.Presenter):RecyclerView.Adapter<IngredientViewAdapter.Holder>() {

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun render(item: Any) {
            when (item) {
                is RecipeModel.Heading -> {
                    itemView.text_name.text = "heading"
                }
                is RecipeModel.FreeText -> {
                    itemView.text_name.text = "free text"
                }
                is RecipeModel.Quantified -> {
                    itemView.text_name.text = "quantified"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewAdapter.Holder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recipe_ingredient_quantified, parent, false)
        return IngredientViewAdapter.Holder(layout)
    }

    override fun onBindViewHolder(holder: IngredientViewAdapter.Holder, position: Int) {
        presenter.onBindIngredientRepositoryRowViewAtPosition(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getIngredientRepositoriesRowsCount()
    }
}
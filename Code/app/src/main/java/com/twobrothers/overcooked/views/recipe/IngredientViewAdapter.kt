package com.twobrothers.overcooked.views.recipe

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.lookups.LookupIngredientType
import com.twobrothers.overcooked.models.recipe.RecipeModel
import kotlinx.android.synthetic.main.fragment_recipe.view.*
import kotlinx.android.synthetic.main.fragment_recipe_ingredient_quantified.view.*

class IngredientViewAdapter(private val presenter: IRecipeContract.Presenter):RecyclerView.Adapter<IngredientViewAdapter.Holder>() {

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun render(item: RecipeModel.Ingredient) {
            when (item) {
                is RecipeModel.Heading -> {
                    itemView.text_title.text = "heading"
                }
                is RecipeModel.FreeText -> {
                    itemView.text_description.text = "free text"
                }
                is RecipeModel.Quantified -> {
                    itemView.text_description.text = "quantified"
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return presenter.getIngredientItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewAdapter.Holder {
        var layoutId:Int = R.layout.fragment_recipe_ingredient_freetext
        when (viewType) {
            LookupIngredientType.Heading.id -> layoutId = R.layout.fragment_recipe_ingredient_heading
            LookupIngredientType.FreeText.id -> layoutId = R.layout.fragment_recipe_ingredient_freetext
            LookupIngredientType.Quantified.id -> layoutId = R.layout.fragment_recipe_ingredient_quantified
        }
        val layout = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return IngredientViewAdapter.Holder(layout)
    }

    override fun onBindViewHolder(holder: IngredientViewAdapter.Holder, position: Int) {
        presenter.onBindIngredientRepositoryRowViewAtPosition(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getIngredientRepositoriesRowsCount()
    }
}
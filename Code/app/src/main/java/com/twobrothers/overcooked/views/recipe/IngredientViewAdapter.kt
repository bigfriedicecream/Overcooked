package com.twobrothers.overcooked.views.recipe

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.app.AppState
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.lookups.LookupIngredientType
import com.twobrothers.overcooked.lookups.LookupIngredientUnitType
import com.twobrothers.overcooked.models.food.FoodResponseModel
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.utils.toFraction
import kotlinx.android.synthetic.main.fragment_recipe.view.*
import kotlinx.android.synthetic.main.fragment_recipe_ingredient_quantified.view.*
import java.text.DecimalFormat

class IngredientViewAdapter(private val presenter: IRecipeContract.Presenter):RecyclerView.Adapter<IngredientViewAdapter.Holder>() {

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun render(item: RecipeModel.Ingredient, foodMap: HashMap<String, FoodResponseModel>) {
            val defaultQuantity = AppState.Recipe.data?.serves ?: AppState.Recipe.data?.makes ?: 1

            when (item) {
                is RecipeModel.Heading -> itemView.text_title.text = item.title
                is RecipeModel.FreeText -> itemView.text_description.text = item.description
                is RecipeModel.Quantified -> {
                    val food = foodMap.getValue(item.foodId)
                    val units = item.unitIds.foldIndexed("") { i, acc, element ->
                        val foodConversion = food.conversions.find { item -> item.unitId == element }

                        foodConversion ?: return

                        val amount = if (item.unitIds.size > 1) item.amount * foodConversion.ratio else item.amount
                        val foodQuantity = when (true) {
                            foodConversion.unitId == LookupIngredientUnitType.Grams.id ||
                                    foodConversion.unitId == LookupIngredientUnitType.Millilitres.id -> Math.ceil(amount / defaultQuantity * AppState.Recipe.activeQuantity)
                            else -> amount / defaultQuantity * AppState.Recipe.activeQuantity
                        }

                        val displayAmount = when (true) {
                            foodQuantity >= 1000 && foodConversion.unitId == LookupIngredientUnitType.Grams.id -> DecimalFormat("###.##").format(foodQuantity / 1000)
                            foodQuantity >= 1000 && foodConversion.unitId == LookupIngredientUnitType.Millilitres.id -> DecimalFormat("###.##").format(foodQuantity / 1000)
                            else -> Double.toFraction(foodQuantity)
                        }

                        val ingredientUnitType = when (true) {
                            foodQuantity >= 1000 && foodConversion.unitId == LookupIngredientUnitType.Grams.id -> LookupIngredientUnitType.Kilograms
                            foodQuantity >= 1000 && foodConversion.unitId == LookupIngredientUnitType.Millilitres.id -> LookupIngredientUnitType.Litres
                            else -> LookupIngredientUnitType.dataLookup(foodConversion.unitId)
                        }

                        val unit = if (foodQuantity > 1) ingredientUnitType.plural else ingredientUnitType.singular

                        if (i == 0) {
                            "$acc$displayAmount$unit"
                        } else {
                            "$acc($displayAmount${unit.trimEnd()}) "
                        }
                    }

                    val foodName = when (food.conversions[food.conversions.lastIndex].unitId) {
                        LookupIngredientUnitType.Slice.id -> food.name.singular
                        LookupIngredientUnitType.Singular.id -> if (item.amount > 1) food.name.plural else food.name.singular
                        else -> food.name.plural
                    }

                    val additionalDesc = if (!item.additionalDesc.isNullOrBlank()) ", ${item.additionalDesc}" else ""

                    val description = "$units$foodName"

                    itemView.text_description.text = description
                    itemView.text_additional_desc.text = additionalDesc
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return presenter.getIngredientItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var layoutId:Int = R.layout.fragment_recipe_ingredient_freetext
        when (viewType) {
            LookupIngredientType.Heading.id -> layoutId = R.layout.fragment_recipe_ingredient_heading
            LookupIngredientType.FreeText.id -> layoutId = R.layout.fragment_recipe_ingredient_freetext
            LookupIngredientType.Quantified.id -> layoutId = R.layout.fragment_recipe_ingredient_quantified
        }
        val layout = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return Holder(layout)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        presenter.onBindIngredientRepositoryRowViewAtPosition(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getIngredientRepositoriesRowsCount()
    }
}
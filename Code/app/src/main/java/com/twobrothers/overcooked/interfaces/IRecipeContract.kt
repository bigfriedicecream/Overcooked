package com.twobrothers.overcooked.interfaces

import android.os.Bundle
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.views.recipe.IngredientViewAdapter
import com.twobrothers.overcooked.views.recipe.MethodViewAdapter


interface IRecipeContract {

    interface View {
        fun render(recipe: RecipeModel, activeQuantity: Int)
        fun onMethodDataSetChanged()
        fun onIngredientDataSetChanged()
    }

    interface Presenter {
        fun onStart(args: Bundle?)
        fun onStop()
        fun reset()
        fun onIncrementQuantity()
        fun onDecrementQuantity()
        fun onBindMethodRepositoryRowViewAtPosition(holder: MethodViewAdapter.Holder, position: Int)
        fun getMethodRepositoriesRowsCount():Int
        fun onBindIngredientRepositoryRowViewAtPosition(holder: IngredientViewAdapter.Holder, position: Int)
        fun getIngredientRepositoriesRowsCount():Int
        fun getIngredientItemViewType(position: Int): Int
    }

}

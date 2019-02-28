package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.views.recipe.MethodViewAdapter


interface IRecipeContract {

    interface View {
        fun render(recipe: RecipeModel)
        fun onMethodDataSetChanged()
    }

    interface Presenter {
        fun onStart()
        fun onStop()
        fun onBindMethodRepositoryRowViewAtPosition(holder: MethodViewAdapter.Holder, position: Int)
        fun getMethodRepositoriesRowsCount():Int
    }

}

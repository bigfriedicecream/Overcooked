package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.utils.RecipeListViewAdapter
import com.twobrothers.overcooked.utils.TheViewHolder


interface IRecipeListContract {

    interface View {
        fun onStart()
        fun onStop()
        fun initAdapter(model: ArrayList<RecipeModel>)
        fun render()
    }

    interface Presenter {
        fun onStart()
        fun onStop()
        fun onBindRepositoryRowViewAtPosition(holder: RepoRowView, position: Int)
        fun getRepositoriesRowsCount():Int
    }

}

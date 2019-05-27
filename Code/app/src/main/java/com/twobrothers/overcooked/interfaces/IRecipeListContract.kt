package com.twobrothers.overcooked.interfaces


interface IRecipeListContract {

    interface View {
        fun onStart()
        fun onStop()
        fun render()
        fun onDataSetChanged(positionStart: Int, itemCount: Int)
    }

    interface Presenter {
        fun onStart()
        fun onStop()
        fun onRecipeListItemClick(position: Int)
        fun onBindRepositoryRowViewAtPosition(holder: IRecipeListViewHolder, position: Int)
        fun getRepositoriesRowsCount():Int
        fun loadRecipes()
    }

}

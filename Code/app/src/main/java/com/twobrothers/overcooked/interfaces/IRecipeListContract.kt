package com.twobrothers.overcooked.interfaces


interface IRecipeListContract {

    interface View {
        fun onStart()
        fun onStop()
        fun onDataSetChanged()
    }

    interface Presenter {
        fun onStart()
        fun onStop()
        fun onBindRepositoryRowViewAtPosition(holder: IRecipeListRowView, position: Int)
        fun getRepositoriesRowsCount():Int
    }

}

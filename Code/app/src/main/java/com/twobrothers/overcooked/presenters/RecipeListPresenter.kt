package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.app.AppState
import com.twobrothers.overcooked.app.Router
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.IRecipeListRowView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class RecipeListPresenter(private val view: IRecipeListContract.View) : IRecipeListContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun onStart() {
        if (AppState.RecipeList.recipes.size > 0) {
            view.render()
            return
        }

        loadRecipes()
    }

    override fun onStop() {
        disposable.dispose()
    }

    override fun loadRecipes() {
        AppState.RecipeList.loadRecipes()
                ?.doOnSuccess {
                    view.render()
                    view.onDataSetChanged(AppState.RecipeList.recipes.count() - it.data.recipes.count(), it.data.recipes.count())
                }
                ?.doOnError {
                    it.printStackTrace()
                }
                ?.subscribe()
                ?.addTo(disposable)
    }

    override fun onRecipeListItemClick(position: Int) {
        Router.goto(Router.Recipe.route(AppState.RecipeList.recipes[position].id))
    }

    override fun onBindRepositoryRowViewAtPosition(holder: IRecipeListRowView, position: Int) {
        holder.render(AppState.RecipeList.recipes[position])
    }

    override fun getRepositoriesRowsCount(): Int {
        return AppState.RecipeList.recipes.size
    }

}

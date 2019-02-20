package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.app.ApiClient
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.RepoRowView
import com.twobrothers.overcooked.models.recipe.RecipeModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class RecipeListPresenter(private val view:IRecipeListContract.View) : IRecipeListContract.Presenter {

    private var recipes = mutableListOf<RecipeModel>()
    private val mDisposable = CompositeDisposable()

    override fun onStart() {
        mDisposable.add(
            ApiClient.getRecipes()
            .subscribeBy(
                    onNext = {
                        recipes.addAll(it.data.recipes)
                        // recipeListItems.add("b")
                        // viewAdapter.notifyItemRangeInserted(recipeListItems.size - 1, 1)
                        view.render()
                    },
                    onError =  { it.printStackTrace() },
                    onComplete = { println("Complete!") }
            )
        )
    }

    override fun onStop() {
        mDisposable.dispose()
    }

    override fun onBindRepositoryRowViewAtPosition(holder: RepoRowView, position: Int) {
        val recipe = recipes[position]
        holder.renderino(recipe)
    }

    override fun getRepositoriesRowsCount(): Int {
        return recipes.size
    }

}

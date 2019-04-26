package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.app.Router
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.IRecipeListRowView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import com.twobrothers.overcooked.app.RecipeListManager
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel

class RecipeListPresenter(private val view:IRecipeListContract.View) : IRecipeListContract.Presenter {

    private val mDisposable = CompositeDisposable()

    override fun onStart() {
        mDisposable.add(
            RecipeListManager.loadRecipes()
            .subscribeBy(
                    onSuccess = {
                        // recipes = it.data.recipes
                        view.render()
                        view.onDataSetChanged()

                        // recipes.addAll(it.data.recipes)
                        // recipeListItems.add("b")
                        // viewAdapter.notifyItemRangeInserted(recipeListItems.size - 1, 1)
                    },
                    onError =  { it.printStackTrace() }
            )
        )
    }

    override fun onStop() {
        mDisposable.dispose()
    }

    override fun onRecipeListItemClick(position: Int) {
        Router.goto(Router.Recipe.route(RecipeListManager.recipes[position].id))
    }

    override fun onBindRepositoryRowViewAtPosition(holder: IRecipeListRowView, position: Int) {
        holder.render(RecipeListManager.recipes[position])
    }

    override fun getRepositoriesRowsCount(): Int {
        return RecipeListManager.recipes.size
    }

    override fun loadMore() {
        println("load more")
    }

}

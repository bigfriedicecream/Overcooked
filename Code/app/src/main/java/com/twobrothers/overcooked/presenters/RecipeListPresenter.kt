package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.app.Router
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.IRecipeListRowView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import com.twobrothers.overcooked.app.RecipeListManager
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel

class RecipeListPresenter(private val view:IRecipeListContract.View) : IRecipeListContract.Presenter {

    private var recipes = mutableListOf<RecipeResponseModel.Recipe>()
    private val mDisposable = CompositeDisposable()

    override fun onStart() {
        mDisposable.add(
            RecipeListManager.getRecipesAt(0)
            .subscribeBy(
                    onSuccess = {
                        recipes = it.data.recipes
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
        Router.goto(Router.Recipe.route(recipes[position].id))
    }

    override fun onBindRepositoryRowViewAtPosition(holder: IRecipeListRowView, position: Int) {
        val recipe = recipes[position]
        holder.render(recipe)
    }

    override fun getRepositoriesRowsCount(): Int {
        return recipes.size
    }

    override fun loadMore() {
        println("load more")
    }

}

package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.app.ApiClient
import com.twobrothers.overcooked.app.Navigation
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.IRecipeListRowView
import com.twobrothers.overcooked.models.recipe.RecipeModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import android.os.Bundle




class RecipeListPresenter(private val view:IRecipeListContract.View) : IRecipeListContract.Presenter {

    private var recipes = mutableListOf<RecipeModel>()
    private val mDisposable = CompositeDisposable()

    override fun onStart() {
        mDisposable.add(
            ApiClient.getRecipes()
            .subscribeBy(
                    onSuccess = {
                        recipes = it.data.recipes
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
        val arguments = Bundle()
        arguments.putString("id", recipes[position].id)
        Navigation.push(Navigation.NavItem("RECIPE_VIEW", arguments))
    }

    override fun onBindRepositoryRowViewAtPosition(holder: IRecipeListRowView, position: Int) {
        val recipe = recipes[position]
        holder.render(recipe)
    }

    override fun getRepositoriesRowsCount(): Int {
        return recipes.size
    }

}

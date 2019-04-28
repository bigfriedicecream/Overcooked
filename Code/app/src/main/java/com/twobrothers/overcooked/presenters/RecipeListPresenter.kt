package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.app.Router
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.interfaces.IRecipeListRowView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import com.twobrothers.overcooked.app.RecipeListManager

class RecipeListPresenter(private val view:IRecipeListContract.View) : IRecipeListContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun onStart() {
        if (RecipeListManager.recipes.size > 0) {
            view.render()
            // view.onDataSetChanged(0, RecipeListManager.recipes.size)
            return
        }

        val request = RecipeListManager.loadRecipes()
                ?.subscribeBy(
                        onSuccess = {
                            view.render()
                            view.onDataSetChanged(RecipeListManager.recipes.count() - it.data.recipes.count(), it.data.recipes.count())
                        },
                        onError =  { it.printStackTrace() }
                )
        if (request != null) {
            disposable.add(request)
        }
    }

    override fun onStop() {
        disposable.dispose()
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

    override fun loadRecipes() {
        if (!RecipeListManager.lastPage && !RecipeListManager.requestInProgress) {
            val request = RecipeListManager.loadRecipes()
                    ?.subscribeBy(
                            onSuccess = {
                                view.onDataSetChanged(RecipeListManager.recipes.count() - it.data.recipes.count(), it.data.recipes.count())
                            },
                            onError =  { it.printStackTrace() }
                    )
            if (request != null) {
                disposable.add(request)
            }
        }
    }

}

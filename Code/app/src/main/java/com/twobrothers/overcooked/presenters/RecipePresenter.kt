package com.twobrothers.overcooked.presenters

import android.os.Bundle
import com.twobrothers.overcooked.app.ApiClient
import com.twobrothers.overcooked.app.AppState
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.lookups.LookupIngredientType
import com.twobrothers.overcooked.views.recipe.IngredientViewAdapter
import com.twobrothers.overcooked.views.recipe.MethodViewAdapter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class RecipePresenter(private val view:IRecipeContract.View) : IRecipeContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun onStart(args: Bundle?) {
        ApiClient
                .getRecipe(args!!.getString("id"))
                .subscribeBy(
                        onSuccess = {
                            AppState.Recipe.data = it
                            view.render(it)
                            view.onMethodDataSetChanged()
                            view.onIngredientDataSetChanged()
                        },
                        onError =  { it.printStackTrace() }
                )
                .addTo(disposable)
    }

    override fun onStop() {
        disposable.dispose()
    }

    override fun onIncrementQuantity() {
        AppState.Recipe.activeQuantity = AppState.Recipe.activeQuantity + 1
        view.onQuantityUpdate(AppState.Recipe.activeQuantity)
    }

    override fun onDecrementQuantity() {
        AppState.Recipe.activeQuantity = AppState.Recipe.activeQuantity -1
        view.onQuantityUpdate(AppState.Recipe.activeQuantity)
    }

    override fun onBindMethodRepositoryRowViewAtPosition(holder: MethodViewAdapter.Holder, position: Int) {
        val recipeModel = AppState.Recipe.data
        recipeModel ?: return
        holder.render(position + 1, recipeModel.method[position])
    }

    override fun getMethodRepositoriesRowsCount(): Int {
        val recipeModel = AppState.Recipe.data
        recipeModel ?: return 0
        return recipeModel.method.size
    }

    override fun onBindIngredientRepositoryRowViewAtPosition(holder: IngredientViewAdapter.Holder, position: Int) {
        val recipeModel = AppState.Recipe.data
        recipeModel ?: return
        holder.render(recipeModel.ingredients[position], recipeModel.food)
    }

    override fun getIngredientRepositoriesRowsCount(): Int {
        val recipeModel = AppState.Recipe.data
        recipeModel ?: return 0
        return recipeModel.ingredients.size
    }

    override fun getIngredientItemViewType(position: Int): Int {
        val recipeModel = AppState.Recipe.data
        recipeModel ?: return -1

        val ingredient = recipeModel.ingredients[position]
        return LookupIngredientType.dataLookup(ingredient.ingredientType).id
    }

}

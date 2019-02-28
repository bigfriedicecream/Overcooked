package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.app.ApiClient
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.interfaces.IRecipeListRowView
import com.twobrothers.overcooked.views.recipe.MethodViewAdapter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class RecipePresenter(private val view:IRecipeContract.View) : IRecipeContract.Presenter {

    private val disposable = CompositeDisposable()
    private var methodList = ArrayList<String>()

    override fun onStart() {
        disposable.add(
                ApiClient.getRecipeById()
                        .subscribeBy(
                                onSuccess = {
                                    view.render(it.data.recipe)
                                    methodList = it.data.recipe.method
                                    view.onMethodDataSetChanged()
                                },
                                onError =  { it.printStackTrace() }
                        )
        )
    }

    override fun onStop() {
        disposable.dispose()
    }

    override fun onBindMethodRepositoryRowViewAtPosition(holder: MethodViewAdapter.Holder, position: Int) {
        val method = methodList[position]
        holder.render(method)
    }

    override fun getMethodRepositoriesRowsCount(): Int {
        return methodList.size
    }

}

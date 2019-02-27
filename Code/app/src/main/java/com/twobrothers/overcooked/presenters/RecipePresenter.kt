package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.app.ApiClient
import com.twobrothers.overcooked.interfaces.IRecipeContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class RecipePresenter(private val view:IRecipeContract.View) : IRecipeContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun onStart() {
        disposable.add(
                ApiClient.getRecipeById()
                        .subscribeBy(
                                onSuccess = {
                                    view.render(it.data.recipe)
                                },
                                onError =  { it.printStackTrace() }
                        )
        )
    }

    override fun onStop() {
        disposable.dispose()
    }

}

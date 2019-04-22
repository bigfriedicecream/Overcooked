package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.app.Router
import com.twobrothers.overcooked.views.recipe.RecipeFragment
import com.twobrothers.overcooked.views.recipelist.RecipeListFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class MainFragment : Fragment() {

    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        disposable.add(
                Router
                        .getInstance()
                        .subscribeBy(
                                onNext = {
                                    val view = when (it.id) {
                                        Router.Recipe.id -> RecipeFragment()
                                        Router.RecipeList.id -> RecipeListFragment()
                                        else -> RecipeListFragment()
                                    }
                                    view.arguments = it.args
                                    fragmentManager
                                            ?.beginTransaction()
                                            ?.replace(R.id.layout_main, view)
                                            ?.addToBackStack(null)
                                            ?.commitAllowingStateLoss()
                                }
                        )
        )

        if (Router.historySize == 0) {
            Router.goto(Router.RecipeList.route())
        }
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}

package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.app.Navigation
import com.twobrothers.overcooked.views.recipe.RecipeFragment
import com.twobrothers.overcooked.views.recipelist.RecipeListFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class MainFragment : Fragment() {

    private val mDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        mDisposable.add(
            Navigation
                .getInstance()
                .subscribeBy(
                    onNext = {
                        if (it == "RECIPE_VIEW") {
                            fragmentManager
                                    ?.beginTransaction()
                                    ?.replace(R.id.layout_main, RecipeFragment())
                                    ?.commitAllowingStateLoss()
                        }
                    }
                )
        )

        fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.layout_main, RecipeFragment()) // RecipeListFragment())
                ?.commitAllowingStateLoss()

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStop() {
        super.onStop()
        mDisposable.dispose()
    }
}

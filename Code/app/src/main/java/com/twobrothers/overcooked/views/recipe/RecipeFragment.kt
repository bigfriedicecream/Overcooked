package com.twobrothers.overcooked.views.recipe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.presenters.RecipePresenter

class RecipeFragment : Fragment(), IRecipeContract.View {

    private val presenter: IRecipeContract.Presenter = RecipePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun render() {

    }

}

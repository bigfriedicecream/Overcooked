package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IMainContract
import com.twobrothers.overcooked.presenters.MainPresenter

class MainFragment : Fragment(), IMainContract.View {

    private var presenter:IMainContract.Presenter? = null
    private var isLoaded:Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        presenter = MainPresenter(this)

        if (state != null) {
            isLoaded = state.getBoolean("isLoaded")
        }

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter?.start(context!!)
        if (!isLoaded) {
            presenter?.load()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter?.stop(context!!)
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        state.putBoolean("isLoaded", isLoaded)
    }

    override fun renderList() {
        val fragmentManager = fragmentManager
        fragmentManager!!.popBackStack("root", FragmentManager.POP_BACK_STACK_INCLUSIVE)

        getFragmentManager()!!
                .beginTransaction()
                .replace(R.id.layout_main, RecipeListFragment())
                .addToBackStack("root")
                .commit()

        isLoaded = true
    }

    override fun renderRecipe(id: String) {
        val recipeFragment = RecipeFragment()

        val bundle = Bundle()
        bundle.putString("id", id)

        recipeFragment.arguments = bundle

        fragmentManager!!
                .beginTransaction()
                .replace(R.id.layout_main, recipeFragment)
                .addToBackStack(null)
                .commit()

        isLoaded = true
    }
}

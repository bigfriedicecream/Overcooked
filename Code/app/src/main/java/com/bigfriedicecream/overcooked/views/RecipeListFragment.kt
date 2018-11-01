package com.bigfriedicecream.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.bigfriedicecream.overcooked.R
import com.bigfriedicecream.overcooked.interfaces.IRecipeListContract
import com.bigfriedicecream.overcooked.models.RecipeModel
import com.bigfriedicecream.overcooked.presenters.RecipeListPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*

import java.util.HashMap

class RecipeListFragment : Fragment(), IRecipeListContract.View {

    private var presenter:IRecipeListContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = RecipeListPresenter(this)
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter?.start(context!!)
    }

    override fun onStop() {
        super.onStop()
        presenter?.stop()
    }

    override fun render(recipeList: HashMap<String, RecipeModel>) {
        view?.layout_list?.removeAllViews()

        for (recipe in recipeList.values) {
            val bundle = Bundle()
            bundle.putString("data", Gson().toJson(recipe))

            val fragment = RecipeListItemFragment()
            fragment.arguments = bundle

            childFragmentManager
                    .beginTransaction()
                    .add(R.id.layout_list, fragment)
                    .commitAllowingStateLoss()
        }
    }
}

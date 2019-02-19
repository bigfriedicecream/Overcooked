package com.twobrothers.overcooked.views.recipelist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.app.ApiClient
import com.twobrothers.overcooked.app.ApiService
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import com.twobrothers.overcooked.presenters.RecipeListPresenter
import com.twobrothers.overcooked.utils.RecipeListViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import io.reactivex.disposables.CompositeDisposable

class RecipeListFragment : Fragment(), IRecipeListContract.View {

    private val presenter: IRecipeListContract.Presenter = RecipeListPresenter(this)

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun initAdapter(model: ArrayList<RecipeModel>) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = RecipeListViewAdapter(presenter, model)

        recyclerView = view!!.findViewById<RecyclerView>(R.id.recycler_container).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun render() {
        // recipes.addAll(model.data.recipes)
        viewAdapter.notifyDataSetChanged()
        // recipeListItems.add("b")
        // viewAdapter.notifyItemRangeInserted(recipeListItems.size - 1, 1)
    }
}

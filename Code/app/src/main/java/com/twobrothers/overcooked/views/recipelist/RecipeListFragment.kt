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
import com.twobrothers.overcooked.app.ApiService
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import com.twobrothers.overcooked.utils.RecipeListViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import io.reactivex.disposables.CompositeDisposable

class RecipeListFragment : Fragment() {

    private val mDisposable = CompositeDisposable()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var recipeListItems = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val api = retrofit.create(ApiService::class.java)

        recipeListItems.add("a")
        viewManager = LinearLayoutManager(context)
        viewAdapter = RecipeListViewAdapter(recipeListItems)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_container).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        mDisposable.add(
                api.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { render(it) },
                        onError =  { it.printStackTrace() },
                        onComplete = { println("Complete!") })
        )

        return view
    }

    override fun onStop() {
        super.onStop()
        mDisposable.dispose()
    }

    private fun render(model:RecipeListModel) {
        recipeListItems.add("b")
        // viewAdapter.notifyDataSetChanged()
        viewAdapter.notifyItemRangeInserted(recipeListItems.size - 1, 1)
    }
}

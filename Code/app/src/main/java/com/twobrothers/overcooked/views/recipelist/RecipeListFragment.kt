package com.twobrothers.overcooked.views.recipelist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.app.ApiService
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import io.reactivex.disposables.CompositeDisposable

class RecipeListFragment : Fragment() {

    private val mDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val api = retrofit.create(ApiService::class.java)

        mDisposable.add(
                api.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { render(it) },
                        onError =  { it.printStackTrace() },
                        onComplete = { println("Complete!") })
        )

        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onStop() {
        super.onStop()
        mDisposable.dispose()
    }

    private fun render(model:RecipeListModel) {
        println(model.data.recipes[0].id)
    }
}

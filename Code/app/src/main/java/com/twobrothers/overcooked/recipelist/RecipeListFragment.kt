package com.twobrothers.overcooked.recipelist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeListContract
import com.twobrothers.overcooked.models.RecipeModel
import com.twobrothers.overcooked.presenters.RecipeListPresenter
import com.google.gson.Gson
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.app.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*
import org.reactivestreams.Subscriber
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.HashMap
import io.reactivex.disposables.CompositeDisposable





class RecipeListFragment : Fragment() {

    val mDisposable = CompositeDisposable()

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
                        onNext = { println(it.recipes) },
                        onError =  { it.printStackTrace() },
                        onComplete = { println("Done!") })
        )

        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onStop() {
        super.onStop()
        mDisposable.dispose()
    }
}

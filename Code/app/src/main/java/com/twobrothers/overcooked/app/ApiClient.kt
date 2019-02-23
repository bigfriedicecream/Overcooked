package com.twobrothers.overcooked.app

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.Overcooked
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val apiService = Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)

    fun getRecipes(): Single<RecipeListModel> {
        val pref = PreferenceManager.getDefaultSharedPreferences(Overcooked.appContext)
        val recipeListCache = pref.getString("recipeListCache", "")
        val mDisposable = CompositeDisposable()

        println("in cache: $recipeListCache")

        val request = apiService
                .getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        mDisposable.add(
            request.subscribeBy(
                onSuccess = {
                    pref.edit().putString("recipeListCache", Gson().toJson(it)).apply()
                    mDisposable.dispose()
                }
        ))

        return request
    }
}
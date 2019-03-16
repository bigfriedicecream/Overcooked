package com.twobrothers.overcooked.app

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.*
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.Overcooked
import com.twobrothers.overcooked.lookups.LookupIngredientType
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val cacheSize = (5 * 1024 * 1024).toLong() // 5MB
    private val myCache = Cache(Overcooked.appContext!!.cacheDir, cacheSize)

    private val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor {
                var request = it.request()
                request = if (hasNetwork()!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build() // 60 seconds
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build() // 7 days
                it.proceed(request)
            }
            .build()


    private val apiService = Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)

    private fun hasNetwork(): Boolean? {
        var isConnected: Boolean? = false
        val connectivityManager = Overcooked.appContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) {
            isConnected = true
        }
        return isConnected
    }

    fun getRecipes(): Single<RecipeListModel> {
        return apiService
                .getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRecipeById(id: String): Single<RecipeModel> {
        return apiService
                .getRecipeById(id)
                .subscribeOn(Schedulers.io())
                .map {
                    val recipe = it.data.recipe
                    val food = it.data.food
                    val ingredients = arrayListOf<Any>()
                    recipe.ingredientSections.forEach {
                        if (it.heading != null) {
                            ingredients.add(RecipeModel.Heading(LookupIngredientType.Heading.id, it.heading))
                        }
                        it.ingredients.forEach {
                            val ingredientType = it.get("ingredientType").asInt
                            when (ingredientType) {
                                0 -> {
                                    val f = Gson().toJson(food[it.get("foodId").asString])
                                    it.add("food", Gson().fromJson(f, JsonObject::class.java))
                                    val quantified = Gson().fromJson<RecipeModel.Quantified>(it.toString(), RecipeModel.Quantified::class.java)
                                    ingredients.add(quantified)
                                }
                                1 -> {
                                    val freeText = Gson().fromJson<RecipeModel.FreeText>(it.toString(), RecipeModel.FreeText::class.java)
                                    ingredients.add(freeText)
                                }
                            }
                        }
                    }
                    RecipeModel(recipe.id, recipe.title, recipe.imageUrl, recipe.method, ingredients)
                }
                .observeOn(AndroidSchedulers.mainThread())
    }
}
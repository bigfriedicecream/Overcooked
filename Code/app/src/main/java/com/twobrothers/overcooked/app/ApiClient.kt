package com.twobrothers.overcooked.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import com.twobrothers.overcooked.utils.mapInPlace
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
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
        return apiService
                .getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        /* val cachedData = CacheService.get("recipeListCache", object : TypeToken<CacheItem<RecipeListModel>>() {}.type, RecipeListModel())

        val mDisposable = CompositeDisposable()
        val request = apiService
                .getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        if (cachedData != null && !cachedData.isExpiring() && !cachedData.isExpired()) {
            return Single.create {
                it.onSuccess(cachedData.data)
            }
        }

        if (cachedData != null && cachedData.isExpiring()) {
            mDisposable.add(
                    request.subscribeBy(
                            onSuccess = {
                                CacheService.put("recipeListCache", it)
                                mDisposable.dispose()
                            }
                    ))
            return Single.create {
                it.onSuccess(cachedData.data)
            }
        }

        mDisposable.add(
                request.subscribeBy(
                        onSuccess = {
                            CacheService.put("recipeListCache", it)
                            mDisposable.dispose()
                        }
                ))
        return request*/
    }

    fun getRecipeById(id: String): Single<RecipeModel> {
        return apiService
                .getRecipeById(id)
                .subscribeOn(Schedulers.io())
                .map {
                    val recipe = it.data.recipe
                    val ingredients = arrayListOf<Any>()
                    recipe.ingredientSections.forEach {
                        if (it.heading != null) {
                            ingredients.add(RecipeModel.Heading(it.heading))
                        }
                        it.ingredients.forEach {
                            val ingredientType = it.get("ingredientType").asInt
                            when (ingredientType) {
                                0 -> {
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
                    println(ingredients)
                    RecipeModel(recipe.id, recipe.title, recipe.imageUrl, recipe.method)
                }
                .observeOn(AndroidSchedulers.mainThread())
    }
}
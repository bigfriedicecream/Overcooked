package com.twobrothers.overcooked.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.models.recipe.RecipeDataModel
import com.twobrothers.overcooked.models.recipe.RecipeModel
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

    fun getRecipeById(id: String): Single<RecipeDataModel> {
        /* return apiService
                .getRecipeById(id)
                .subscribeOn(Schedulers.io())
                .map {
                    RecipeModel(it)
                }
                .observeOn(AndroidSchedulers.mainThread())*/

        val recipeResponseDeserializer = GsonBuilder().registerTypeAdapter(RecipeDataModel::class.java, RecipeResponseDeserializer()).create()

        val api = Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(recipeResponseDeserializer))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)

        return api
                .getRecipeById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /* private fun mapThings(recipeDataModel: RecipeResponseModel): RecipeResponseModel {
        recipeDataModel.data.recipe.ingredientSections.map {
            it.ingredients.mapInPlace {
                val ingredient = Gson().fromJson(Gson().toJson(it), RecipeModel.Ingredient::class.java)
                if (ingredient.ingredientType == 0) {
                    Gson().fromJson(Gson().toJson(it), RecipeModel.Quantified::class.java)
                } else {
                    Gson().fromJson(Gson().toJson(it), RecipeModel.FreeText::class.java)
                }
            }
        }

        return recipeDataModel
    }*/
}
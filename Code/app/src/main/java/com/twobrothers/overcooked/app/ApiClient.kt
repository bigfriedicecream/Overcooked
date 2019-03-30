package com.twobrothers.overcooked.app

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.models.recipelist.RecipeListResponseModel
import com.twobrothers.overcooked.utils.CacheItem
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

    private fun <T>requestViaCache(cache: CacheItem<T>?, request: Single<T>): Single<T> {
        if (cache != null && cache.isFresh()) {
            return Single.create {
                it.onSuccess(cache.data)
            }
        }

        if (cache != null && cache.isExpiring()) {
            request.subscribe()
            return Single.create {
                it.onSuccess(cache.data)
            }
        }

        return request
    }

    fun getRecipesAt(page: Int): Single<RecipeListResponseModel> {
        val cache = CacheService.get("recipeList", object: TypeToken<CacheItem<RecipeListResponseModel>>(){}.type, RecipeListResponseModel::class.java)
        val request = apiService
                .getRecipesAt(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess {
                    CacheService.put("recipeList", it, 1000 * 60 * 60 * 8)
                }

        return requestViaCache(cache, request)
    }

    fun getRecipe(id: String): Single<RecipeResponseModel> {
        return apiService
                .getRecipe(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
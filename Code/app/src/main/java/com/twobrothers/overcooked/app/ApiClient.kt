package com.twobrothers.overcooked.app

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.lookups.LookupIngredientType
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
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
                println("return from cache")
                it.onSuccess(cache.data)
            }
        }

        if (cache != null && cache.isExpiring()) {
            println("is expiring")
            request.subscribe()
            return Single.create {
                println("return from expiring cache")
                it.onSuccess(cache.data)
            }
        }

        println("fetch new")
        return request
    }

    fun getRecipes(): Single<RecipeListModel> {
        val cache = CacheService.get("recipeList", object: TypeToken<CacheItem<RecipeListModel>>(){}.type, RecipeListModel::class.java)
        val request = apiService
                .getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess {
                    println("put in cache")
                    CacheService.put("recipeList", it, 1000 * 60 * 2)
                }

        return requestViaCache(cache, request)
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
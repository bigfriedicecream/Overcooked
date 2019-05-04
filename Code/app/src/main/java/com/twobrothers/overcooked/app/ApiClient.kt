package com.twobrothers.overcooked.app

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.lookups.LookupIngredientType
import com.twobrothers.overcooked.models.food.FoodResponseModel
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

    fun getRecipesAt(page: Int): Single<RecipeListResponseModel> {
        return apiService
                .getRecipesAt(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRecipe(id: String): Single<RecipeModel> {
        val cache = CacheService.get("recipe-$id", object: TypeToken<CacheItem<RecipeResponseModel.Recipe>>(){}.type, RecipeResponseModel.Recipe::class.java)

        fun mapRecipe(it: RecipeResponseModel.Recipe): RecipeModel {
            val ingredients = ArrayList<RecipeModel.Ingredient>()
            val food = HashMap<String, FoodResponseModel>()

            it.ingredientSections.forEach {
                if (!it.heading.isNullOrBlank()) {
                    ingredients.add(RecipeModel.Heading(LookupIngredientType.Heading.id, it.heading))
                }
                it.ingredients.forEach {
                    when (it.get("ingredientType").asInt) {
                        LookupIngredientType.FreeText.id -> ingredients.add(Gson().fromJson(it, RecipeModel.FreeText::class.java))
                        LookupIngredientType.Quantified.id -> {
                            val quantified = Gson().fromJson(it, RecipeModel.Quantified::class.java)
                            ingredients.add(quantified)
                            food[quantified.foodId] = FoodManager.getFood(quantified.foodId)!!
                        }
                    }
                }
            }

            return RecipeModel(
                    it.id,
                    it.title,
                    it.prepTime,
                    it.cookTime,
                    ingredients,
                    it.method,
                    it.referenceUrl,
                    it.imageUrl,
                    it.lastUpdated,
                    it.serves,
                    it.makes,
                    food
            )
        }

        val request = apiService
                .getRecipe(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    CacheService.put("recipe-${it.data.recipe.id}", it, 1000 * 60 * 60 * 24)
                    mapRecipe(it.data.recipe)
                }

        if (cache != null && cache.isFresh()) {
            return Single.create {
                it.onSuccess(
                        mapRecipe(cache.data)
                )
            }
        }

        if (cache != null && cache.isExpiring()) {
            request.subscribe()
            return Single.create {
                it.onSuccess(
                        mapRecipe(cache.data)
                )
            }
        }

        return request
    }
}
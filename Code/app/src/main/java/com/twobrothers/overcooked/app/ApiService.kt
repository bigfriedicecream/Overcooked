package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeResponseModel
import com.twobrothers.overcooked.models.recipelist.RecipeListResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("recipes/at/{page}")
    fun getRecipesAt(@Path("page") id: Int): Single<RecipeListResponseModel>

    @GET("recipes/{id}")
    fun getRecipe(@Path("id") id: String): Single<RecipeResponseModel>

}
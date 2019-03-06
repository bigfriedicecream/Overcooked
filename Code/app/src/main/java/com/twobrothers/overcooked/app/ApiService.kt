package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeDataModel
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("recipes/at/0")
    fun getRecipes(): Single<RecipeListModel>

    @GET("recipes/{id}")
    fun getRecipeById(@Path("id") id: String): Single<RecipeDataModel>

}
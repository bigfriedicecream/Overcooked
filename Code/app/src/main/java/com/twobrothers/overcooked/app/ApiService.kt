package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeDataModel
import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("recipes/at/0")
    fun getRecipes(): Single<RecipeListModel>

    @GET("recipes/5c70c8fdf36d3de9a97aee3e")
    fun getRecipeById(): Single<RecipeDataModel>

}
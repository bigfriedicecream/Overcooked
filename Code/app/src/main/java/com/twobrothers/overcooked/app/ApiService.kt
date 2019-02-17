package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipelist.RecipeListModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("recipes/at/0")
    fun getRecipes(): Observable<RecipeListModel>

}
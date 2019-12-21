package com.twobrothers.overcooked.core

import com.twobrothers.overcooked.core.responses.FirebaseRecipeList
import retrofit2.Response
import retrofit2.http.GET

/**
 * Defines all the endpoints provided by the Firebase API. This interface is then implemented by Retrofit.
 */
interface FirebaseApiService {
    @GET("/getRecipeList")
    suspend fun getRecipes(): Response<FirebaseRecipeList>
}
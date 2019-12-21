package com.twobrothers.overcooked.core

import com.twobrothers.overcooked.recipedetails.models.Recipe
import com.twobrothers.overcooked.recipelibrary.models.RecipeSummary
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirebaseApiDataSource {
    private var service: FirebaseApiService

    init {
        // Initialise client
        val clientBuilder = OkHttpClient.Builder()

        // Create service
        val retrofit = Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl("https://us-central1-overcooked-d7779.cloudfunctions.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(FirebaseApiService::class.java)
    }

    suspend fun getRecipes(): List<RecipeSummary>? {
        return service.getRecipes().body()?.data?.toRecipeSummaryList()
    }

    suspend fun getRecipe(id: String): Recipe? {
        return service.getRecipe(id).body()?.data?.toRecipe()
    }
}
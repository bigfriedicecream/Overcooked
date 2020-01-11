package com.twobrothers.overcooked.core.datasource

import com.twobrothers.overcooked.BuildConfig
import com.twobrothers.overcooked.core.App
import com.twobrothers.overcooked.recipedetails.models.Recipe
import com.twobrothers.overcooked.recipelibrary.models.RecipeSummary
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class FirebaseApiDataSource @Inject constructor() {
    private var service: FirebaseApiService

    init {
        // Initialise client
        val clientBuilder = OkHttpClient.Builder()

        // Set up 5MB cache
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(App.getAppContext().cacheDir, cacheSize)

        clientBuilder.cache(cache)

        clientBuilder.addInterceptor { chain ->
            var request = chain.request()
            request = if (hasNetwork(App.getAppContext()))
                request.newBuilder().header(
                    "Cache-Control",
                    "public, max-age=" + 60 * 60
                ).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }

        // Create service
        val retrofit = Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl("https://us-central1-overcooked-d7779.cloudfunctions.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(FirebaseApiService::class.java)
    }

    suspend fun getRecipes(): List<RecipeSummary>? {
        return service.getRecipes(1.1, BuildConfig.ENV).body()?.data?.toRecipeSummaryList()
    }

    suspend fun getRecipe(id: String): Recipe? {
        return service.getRecipe(1.1, BuildConfig.ENV, id).body()?.data?.toRecipe()
    }
}
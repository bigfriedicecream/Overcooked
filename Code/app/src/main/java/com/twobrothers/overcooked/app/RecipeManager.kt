package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeModel
import io.reactivex.Single

object RecipeManager {

    var recipeModel: RecipeModel? = null
        private set

    fun getRecipe(id: String): Single<RecipeModel> {
        return ApiClient.getRecipe(id)
                .doOnSuccess {
                    recipeModel = it
                }
    }

}
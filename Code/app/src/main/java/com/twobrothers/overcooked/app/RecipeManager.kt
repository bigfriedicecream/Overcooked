package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeModel
import io.reactivex.Single

object RecipeManager {

    fun getRecipe(id: String): Single<RecipeModel> {
        return ApiClient.getRecipe(id)
    }

}
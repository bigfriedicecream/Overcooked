package com.twobrothers.overcooked.app

import com.twobrothers.overcooked.models.recipe.RecipeModel
import io.reactivex.Single

object AppState {
    object Recipe {
        var data: RecipeModel? = null
            private set
        var activeQuantity: Int = -1
            private set

        fun load(id: String): Single<RecipeModel> {
            return ApiClient
                    .getRecipe(id)
                    .doOnSuccess {
                        data = it
                        activeQuantity = it?.serves ?: it?.makes ?: 0
                    }
        }

        fun updateActiveQuantity(quantity: Int) {
            activeQuantity = quantity
        }
    }
}
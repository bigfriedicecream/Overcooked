package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.recipe.RecipeModel

interface RepoRowView {
    fun renderino(recipe: RecipeModel)
}
package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.recipe.RecipeModel

interface IRecipeListRowView {
    fun render(recipe: RecipeModel)
}
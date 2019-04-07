package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.recipe.RecipeResponseModel

interface IRecipeListRowView {
    fun render(recipe: RecipeResponseModel.Recipe)
}
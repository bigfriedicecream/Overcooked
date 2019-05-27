package com.twobrothers.overcooked.interfaces

import com.twobrothers.overcooked.models.recipe.RecipeResponseModel

interface IRecipeListViewHolder {
    fun render(recipe: RecipeResponseModel.Recipe)
}
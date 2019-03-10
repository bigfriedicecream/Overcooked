package com.twobrothers.overcooked.models.recipe

data class RecipeDataModel (
        val data: Data?
)

data class Data (
        val recipe: Recipe,
        val happy: String
)

data class Recipe (
        val id: String,
        val title: String,
        val imageUrl: String,
        val method: ArrayList<String>
)
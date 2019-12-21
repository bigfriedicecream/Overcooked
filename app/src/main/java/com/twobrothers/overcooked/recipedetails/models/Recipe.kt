package com.twobrothers.overcooked.recipedetails.models

data class Recipe(
    val id: String,
    val title: String,
    val serves: Int,
    val prepTime: Int,
    val cookTime: Int,
    val method: List<String>
)
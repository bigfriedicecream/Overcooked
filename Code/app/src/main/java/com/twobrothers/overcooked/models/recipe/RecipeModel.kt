package com.twobrothers.overcooked.models.recipe

data class RecipeModel (
    val id: String,
    val title: String,
    val imageUrl: String,
    val method: ArrayList<String>
    // val ingredients: ArrayList<Any>
)
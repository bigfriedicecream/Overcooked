package com.twobrothers.overcooked.core.firebase.models

data class FirebaseRecipe(
    val id: String = "",
    val title: String = "",
    val serves: Int = 0,
    val prepTime: Int = 0,
    val cookTime: Int = 0
)
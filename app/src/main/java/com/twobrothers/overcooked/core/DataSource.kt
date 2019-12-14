package com.twobrothers.overcooked.core

import com.google.firebase.firestore.FirebaseFirestore
import com.twobrothers.overcooked.core.firebase.models.FirebaseRecipe
import com.twobrothers.overcooked.recipedetails.models.Recipe

fun getRecipes(result: OnDataSourceResult<List<Recipe>>) {
    FirebaseFirestore
        .getInstance()
        .collection("fl_content")
        .whereEqualTo("_fl_meta_.schema", "recipes")
        .get()
        .addOnSuccessListener {
            val recipes = it.map {
                val firebaseRecipe = it.toObject(FirebaseRecipe::class.java)
                Recipe(
                    it.id,
                    firebaseRecipe.title
                )
            }
            result.onSuccess(recipes)
        }
}

interface OnDataSourceResult<T> {
    fun onSuccess(result: T)
}
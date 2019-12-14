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
        .addOnFailureListener {
            result.onFailure("Failed to get list of recipes")
        }
}

fun getRecipe(id: String, result: OnDataSourceResult<Recipe>) {
    FirebaseFirestore
        .getInstance()
        .collection("fl_content")
        .document(id)
        .get()
        .addOnSuccessListener {
            val firebaseRecipe = it.toObject(FirebaseRecipe::class.java)
            if (firebaseRecipe == null) {
                result.onFailure("Failed to get recipe $id")
                return@addOnSuccessListener
            }
            val recipe = Recipe(
                it.id,
                firebaseRecipe.title
            )
            result.onSuccess(recipe)
        }
        .addOnFailureListener {
            result.onFailure("Failed to get recipe $id")
        }
}

interface OnDataSourceResult<T> {
    fun onSuccess(result: T)
    fun onFailure(result: String)
}
package com.twobrothers.overcooked.recipelibrary

import com.google.firebase.firestore.FirebaseFirestore

class RecipeLibraryViewModel {

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        // TODO: create an api util class
        FirebaseFirestore
            .getInstance()
            .collection("fl_content")
            .whereEqualTo("_fl_meta_.schema", "recipes")
            .get()
            .addOnSuccessListener {
                it.forEach {
                    println(it)
                }
            }
    }
}
package com.twobrothers.overcooked.core.responses

import com.twobrothers.overcooked.recipelibrary.models.RecipeSummary

data class FirebaseRecipeList(
    val data: FirebaseRecipeListData
)

data class FirebaseRecipeListData(
    val recipes: List<FirebaseRecipeSummary>
) {
    fun toRecipeSummaryList() = recipes.map {
        it.toRecipeSummary()
    }
}

data class FirebaseRecipeSummary(
    val id: String,
    val title: String
) {
    fun toRecipeSummary() = RecipeSummary(
        id = id,
        title = title
    )
}
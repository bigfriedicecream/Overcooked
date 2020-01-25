package com.twobrothers.overcooked.core.responses

import com.twobrothers.overcooked.recipelibrary.models.RecipeSummary
import org.threeten.bp.Duration

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
    val title: String,
    val heroImageUrl: String,
    val prepTime: Long,
    val cookTime: Long
) {
    fun toRecipeSummary() = RecipeSummary(
        id = id,
        title = title,
        heroImageUrl = heroImageUrl,
        prepTime = Duration.ofMinutes(prepTime),
        cookTime = Duration.ofMinutes(cookTime)
    )
}
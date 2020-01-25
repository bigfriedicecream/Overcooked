package com.twobrothers.overcooked.recipelibrary.models

import android.content.Context
import com.twobrothers.overcooked.recipedetails.presentation.getFormattedDuration
import org.threeten.bp.Duration

data class RecipeSummary(
    val id: String,
    val title: String,
    val heroImageUrl: String,
    val prepTime: Duration,
    val cookTime: Duration
) {
    fun getTotalTime(context: Context) = getFormattedDuration(context, prepTime + cookTime)
}
package com.bigfriedicecream.recipes.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class RecipeResponseModel {
    public @SerializedName("recipes") HashMap recipes;
}

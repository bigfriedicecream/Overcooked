package com.bigfriedicecream.recipes.interfaces;

import android.content.Context;

import com.bigfriedicecream.recipes.models.RecipeDataModel;

import java.util.Map;

public interface IRecipeListContract {

    interface View {
        void render(Map<String, RecipeDataModel> recipeList);
    }

    interface Presenter {
        void load(Context context);
    }
}

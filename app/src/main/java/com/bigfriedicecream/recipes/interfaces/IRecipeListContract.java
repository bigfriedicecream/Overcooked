package com.bigfriedicecream.recipes.interfaces;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public interface IRecipeListContract {

    interface View {
        void render(Map<String, Object> recipeList);
    }

    interface Presenter {
        void load(Context context);
    }
}

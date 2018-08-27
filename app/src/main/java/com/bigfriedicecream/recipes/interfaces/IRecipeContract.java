package com.bigfriedicecream.recipes.interfaces;

import com.bigfriedicecream.recipes.models.RecipeDataModel;

public interface IRecipeContract {

    interface View {
        void render(RecipeDataModel recipe);
    }

    interface Presenter {
        void start();
        void stop();
        void load(String id);
    }
}

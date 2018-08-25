package com.bigfriedicecream.recipes.interfaces;

public interface IRecipeContract {

    interface View {
        void render();
    }

    interface Presenter {
        void start();
        void stop();
    }
}

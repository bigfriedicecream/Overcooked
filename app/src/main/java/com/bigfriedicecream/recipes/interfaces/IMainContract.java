package com.bigfriedicecream.recipes.interfaces;

public interface IMainContract {

    interface View {
        void renderList();
        void renderRecipe();
    }

    interface Presenter {
        void start();
        void stop();
        void load();
    }
}

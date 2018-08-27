package com.bigfriedicecream.recipes.interfaces;

public interface IMainContract {

    interface View {
        void renderList();
        void renderRecipe(String id);
    }

    interface Presenter {
        void start();
        void stop();
        void load();
    }
}

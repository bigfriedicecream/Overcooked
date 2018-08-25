package com.bigfriedicecream.recipes.interfaces;

public interface IMainContract {

    interface View {
        void render();
    }

    interface Presenter {
        void start();
        void stop();
    }
}

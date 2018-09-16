package com.bigfriedicecream.recipes.interfaces;

import android.content.Context;

public interface IMainContract {

    interface View {
        void renderList();
        void renderRecipe(String id);
    }

    interface Presenter {
        void start(Context c);
        void stop(Context c);
        void load();
    }
}

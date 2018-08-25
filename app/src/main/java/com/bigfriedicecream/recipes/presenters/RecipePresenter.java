package com.bigfriedicecream.recipes.presenters;

import com.bigfriedicecream.recipes.interfaces.IRecipeContract;

public class RecipePresenter implements IRecipeContract.Presenter {

    private IRecipeContract.View view;

    public RecipePresenter(IRecipeContract.View v) {
        view = v;
    }

    public void start() {
        view.render();
    }

    public void stop() {

    }
}

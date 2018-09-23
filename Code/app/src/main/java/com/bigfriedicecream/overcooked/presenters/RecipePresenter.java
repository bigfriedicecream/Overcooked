package com.bigfriedicecream.overcooked.presenters;

import com.bigfriedicecream.overcooked.interfaces.IRecipeContract;
import com.bigfriedicecream.overcooked.models.RecipeDataModel;
import com.bigfriedicecream.overcooked.observables.RecipeRepository;

import java.util.Observable;
import java.util.Observer;

public class RecipePresenter implements IRecipeContract.Presenter, Observer {

    private IRecipeContract.View view;
    private RecipeRepository recipeRepository;

    public RecipePresenter(IRecipeContract.View v) {
        view = v;
        recipeRepository = RecipeRepository.getInstance();
    }

    public void start() {
        recipeRepository.addObserver(this);
    }

    public void stop() {
        recipeRepository.deleteObserver(this);
    }

    public void load(String id) {
        RecipeDataModel recipe = recipeRepository.get(id);
        if (recipe != null) {
            view.render(recipe);
        }
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}

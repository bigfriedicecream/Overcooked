package com.bigfriedicecream.recipes.presenters;

import android.content.Context;

import com.bigfriedicecream.recipes.interfaces.IRecipeListContract;
import com.bigfriedicecream.recipes.models.RecipeDataModel;
import com.bigfriedicecream.recipes.observables.RecipeRepository;
import com.bigfriedicecream.recipes.models.RecipeResponseDataModel;
import com.google.gson.GsonBuilder;
import com.koushikdutta.async.future.FutureCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class RecipeListPresenter implements IRecipeListContract.Presenter, Observer {

    private IRecipeListContract.View view;
    private RecipeRepository recipeRepository;

    public RecipeListPresenter(IRecipeListContract.View v) {
        view = v;
        recipeRepository = RecipeRepository.getInstance();
    }

    public void start(Context c) {
        recipeRepository.addObserver(this);
        recipeRepository.loadList(c, new FutureCallback<HashMap<String, RecipeDataModel>>() {
            @Override
            public void onCompleted(Exception e, HashMap<String, RecipeDataModel> result) {
                view.render(result);
            }
        });
    }

    public void stop() {
        recipeRepository.deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof RecipeRepository) {
            view.render(recipeRepository.getList());
        }
    }

}

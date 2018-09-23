package com.bigfriedicecream.overcooked.presenters;

import android.content.Context;

import com.bigfriedicecream.overcooked.interfaces.IRecipeListContract;
import com.bigfriedicecream.overcooked.models.RecipeDataModel;
import com.bigfriedicecream.overcooked.observables.RecipeRepository;
import com.bigfriedicecream.overcooked.models.RecipeResponseDataModel;
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
        Map<String, RecipeDataModel> list = recipeRepository.getList();
        if (list != null) {
            view.render(list);
        }
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

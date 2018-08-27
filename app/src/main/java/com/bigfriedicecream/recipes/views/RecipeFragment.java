package com.bigfriedicecream.recipes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigfriedicecream.recipes.R;
import com.bigfriedicecream.recipes.interfaces.IRecipeContract;
import com.bigfriedicecream.recipes.presenters.RecipePresenter;

public class RecipeFragment extends Fragment implements IRecipeContract.View {

    IRecipeContract.Presenter presenter;
    private String id;
    private boolean isLoaded;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle state) {
        presenter = new RecipePresenter(this);

        id = getArguments().getString("id");

        if (state != null) {
            isLoaded = state.getBoolean("isLoaded");
        }

        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
        if (!isLoaded) {
            presenter.load(id);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state) {
        super.onSaveInstanceState(state);
        state.putBoolean("isLoaded", isLoaded);
    }

    public void render() {
        isLoaded = true;
    }
}

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new RecipePresenter(this);
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    public void render() {

    }
}

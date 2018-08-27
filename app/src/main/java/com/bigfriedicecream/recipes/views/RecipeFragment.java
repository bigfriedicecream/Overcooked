package com.bigfriedicecream.recipes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigfriedicecream.recipes.R;
import com.bigfriedicecream.recipes.interfaces.IRecipeContract;
import com.bigfriedicecream.recipes.models.RecipeDataModel;
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

    public void render(RecipeDataModel recipe) {

        String serves = "Serves: " + String.valueOf(recipe.serves);
        String makes = "Makes: " + String.valueOf(recipe.makes);
        String prepTime = "Prep: " + String.valueOf(recipe.prepTime) + " Min";
        String cookTime = "Cook: " + String.valueOf(recipe.cookTime) + " Min";

        ((TextView)getView().findViewById(R.id.title)).setText(recipe.title);
        ((TextView)getView().findViewById(R.id.serves)).setText(serves);
        ((TextView)getView().findViewById(R.id.makes)).setText(makes);
        ((TextView)getView().findViewById(R.id.prep_time)).setText(prepTime);
        ((TextView)getView().findViewById(R.id.cook_time)).setText(cookTime);
        isLoaded = true;
    }
}

package com.bigfriedicecream.recipes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigfriedicecream.recipes.R;
import com.bigfriedicecream.recipes.interfaces.IRecipeListContract;
import com.bigfriedicecream.recipes.models.RecipeResponseModel;
import com.bigfriedicecream.recipes.presenters.RecipeListPresenter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecipeListFragment extends Fragment implements IRecipeListContract.View {

    private IRecipeListContract.Presenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new RecipeListPresenter(this);
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load(getContext());
    }

    public void render(Map<String, Object> recipeList) {
        for (Map.Entry<String, Object> entry : recipeList.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key);
            System.out.println(value);
        }
    }
}

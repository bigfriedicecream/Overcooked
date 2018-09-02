package com.bigfriedicecream.recipes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigfriedicecream.recipes.R;
import com.bigfriedicecream.recipes.interfaces.IRecipeListContract;
import com.bigfriedicecream.recipes.models.RecipeDataModel;
import com.bigfriedicecream.recipes.presenters.RecipeListPresenter;
import com.google.gson.Gson;

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
        presenter.start(getContext());
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    public void render(Map<String, RecipeDataModel> recipeList) {
        LinearLayout layoutList = getView().findViewById(R.id.layout_list);
        layoutList.removeAllViews();

        for (Map.Entry<String, RecipeDataModel> entry : recipeList.entrySet()) {
            Bundle bundle = new Bundle();
            bundle.putString("data", new Gson().toJson(entry.getValue()));

            Fragment fragment = new RecipeListItemFragment();
            fragment.setArguments(bundle);

            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.layout_list, fragment)
                    .commit();
        }
    }
}

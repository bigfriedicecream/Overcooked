package com.bigfriedicecream.overcooked.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigfriedicecream.overcooked.R;
import com.bigfriedicecream.overcooked.interfaces.IRecipeListContract;
import com.bigfriedicecream.overcooked.models.CondensedRecipeModel;
import com.bigfriedicecream.overcooked.models.RecipeModel;
import com.bigfriedicecream.overcooked.presenters.RecipeListPresenter;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
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

    public void render(@NotNull HashMap<String, RecipeModel> recipes) {
        LinearLayout layoutList = getView().findViewById(R.id.layout_list);
        layoutList.removeAllViews();
        /* for (RecipeModel recipe : recipes) {
            Bundle bundle = new Bundle();
            bundle.putString("data", new Gson().toJson(recipe));

            Fragment fragment = new RecipeListItemFragment();
            fragment.setArguments(bundle);

            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.layout_list, fragment)
                    .commitAllowingStateLoss();
        }*/

        for (RecipeModel recipe : recipes.values()) {
            Bundle bundle = new Bundle();
            bundle.putString("data", new Gson().toJson(recipe));

            Fragment fragment = new RecipeListItemFragment();
            fragment.setArguments(bundle);

            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.layout_list, fragment)
                    .commitAllowingStateLoss();
        }
    }

    /*public void render(Map<String, RecipeDataModel> recipeList) {
        LinearLayout layoutList = getView().findViewById(R.id.layout_list);
        layoutList.removeAllViews();

        if (recipeList != null) {
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
    }*/
}

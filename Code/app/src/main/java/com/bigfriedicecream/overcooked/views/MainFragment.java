package com.bigfriedicecream.overcooked.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigfriedicecream.overcooked.R;
import com.bigfriedicecream.overcooked.interfaces.IMainContract;
import com.bigfriedicecream.overcooked.presenters.MainPresenter;

public class MainFragment extends Fragment implements IMainContract.View {

    private IMainContract.Presenter presenter;
    private boolean isLoaded;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle state) {
        presenter = new MainPresenter(this);

        if (state != null) {
            isLoaded = state.getBoolean("isLoaded");
        }

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start(getContext());
        if (!isLoaded) {
            presenter.load();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop(getContext());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state) {
        super.onSaveInstanceState(state);
        state.putBoolean("isLoaded", isLoaded);
    }

    public void renderList() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack("root", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main, new RecipeListFragment())
                .addToBackStack("root")
                .commit();

        isLoaded = true;
    }

    public void renderRecipe(String id) {
        Fragment recipeFragment = new RecipeFragment();

        Bundle bundle = new Bundle();
        bundle.putString("id", id);

        recipeFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main, recipeFragment)
                .addToBackStack(null)
                .commit();

        isLoaded = true;
    }
}

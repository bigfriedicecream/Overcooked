package com.bigfriedicecream.recipes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigfriedicecream.recipes.R;
import com.bigfriedicecream.recipes.interfaces.IMainContract;
import com.bigfriedicecream.recipes.presenters.MainPresenter;

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
        presenter.start();
        if (!isLoaded) {
            presenter.load();
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

    public void renderRecipe() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main, new RecipeFragment())
                .addToBackStack(null)
                .commit();

        isLoaded = true;
    }
}

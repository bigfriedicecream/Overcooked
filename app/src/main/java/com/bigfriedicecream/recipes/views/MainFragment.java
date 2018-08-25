package com.bigfriedicecream.recipes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigfriedicecream.recipes.R;
import com.bigfriedicecream.recipes.interfaces.IMainContract;
import com.bigfriedicecream.recipes.presenters.MainPresenter;

public class MainFragment extends Fragment implements IMainContract.View {

    private IMainContract.Presenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new MainPresenter(this);
        return inflater.inflate(R.layout.fragment_main, container, false);
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

    public void renderList() {
        LinearLayout layoutList = getView().findViewById(R.id.layout_main);
        layoutList.removeAllViews();

        getFragmentManager()
                .beginTransaction()
                .add(R.id.layout_main, new RecipeListFragment())
                .commit();
    }

    public void renderRecipe() {
        LinearLayout layoutList = getView().findViewById(R.id.layout_main);
        layoutList.removeAllViews();

        getFragmentManager()
                .beginTransaction()
                .add(R.id.layout_main, new RecipeFragment())
                .commit();
    }
}

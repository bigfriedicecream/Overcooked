package com.bigfriedicecream.recipes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigfriedicecream.recipes.R;
import com.bigfriedicecream.recipes.models.RecipeResponseModel;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.layout_main, new RecipeListFragment())
                    .commit();
        }

        return view;
    }
}

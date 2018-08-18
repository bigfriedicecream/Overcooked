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

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainFragment extends Fragment {

    String json = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        try {
            InputStream inputStream = getContext().getAssets().open("recipes.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

            // gson
            GsonBuilder builder = new GsonBuilder();
            RecipeResponseModel o = builder.create().fromJson(json, RecipeResponseModel.class);
            System.out.println("recipes");
            System.out.println(o.recipes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
}

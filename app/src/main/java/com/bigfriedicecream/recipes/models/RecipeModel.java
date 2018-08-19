package com.bigfriedicecream.recipes.models;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

public class RecipeModel {

    public static RecipeResponseDataModel getList(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("recipes.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            // gson
            return  new GsonBuilder().create().fromJson(json, RecipeResponseDataModel.class);

        } catch (IOException e) {
            e.printStackTrace();
            return new RecipeResponseDataModel();
        }
    }
}

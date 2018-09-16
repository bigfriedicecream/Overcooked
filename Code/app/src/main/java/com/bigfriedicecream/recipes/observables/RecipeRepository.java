package com.bigfriedicecream.recipes.observables;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bigfriedicecream.recipes.models.RecipeDataModel;
import com.bigfriedicecream.recipes.models.RecipeResponseDataModel;
import com.google.gson.GsonBuilder;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Observable;

public class RecipeRepository extends Observable {

    private static RecipeRepository instance;
    private RecipeResponseDataModel model = null;

    private RecipeRepository() {}

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public void load(Context c) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
        final String recipeResponse = preferences.getString("recipe_response", "");

        if (!recipeResponse.equalsIgnoreCase("")) {
            model = new GsonBuilder().create().fromJson(recipeResponse, RecipeResponseDataModel.class);
            setChanged();
            notifyObservers();
            Ion
                    .with(c)
                    .load("https://raw.githubusercontent.com/bigfriedicecream/Recipes/develop/Assets/recipes.json")
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("recipe_response", result);
                            editor.apply();
                        }
                    });

        } else {
            Ion
                    .with(c)
                    .load("https://raw.githubusercontent.com/bigfriedicecream/Recipes/develop/Assets/recipes.json")
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("recipe_response", result);
                            editor.apply();
                            model = new GsonBuilder().create().fromJson(result, RecipeResponseDataModel.class);
                            setChanged();
                            notifyObservers();
                        }
                    });
        }
    }

    public Map<String, RecipeDataModel> getList() {
        return model == null ? null : model.recipes;
    }

    public RecipeDataModel get(String id) {
        return model == null ? null : model.recipes.get(id);
    }
}

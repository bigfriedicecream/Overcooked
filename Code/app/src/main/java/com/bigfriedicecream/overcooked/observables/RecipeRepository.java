package com.bigfriedicecream.overcooked.observables;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bigfriedicecream.overcooked.BuildConfig;
import com.bigfriedicecream.overcooked.models.RecipeDataModel;
import com.bigfriedicecream.overcooked.models.RecipeResponseDataModel;
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

    public void load(Context context) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String recipeResponse = preferences.getString("overcooked_database", "");

        if (!recipeResponse.equalsIgnoreCase("")) {
            model = new GsonBuilder().create().fromJson(recipeResponse, RecipeResponseDataModel.class);
            setChanged();
            notifyObservers();

        } else {
            Ion
                    .with(context)
                    .load("https://firebasestorage.googleapis.com/v0/b/overcooked-f5fc4.appspot.com/o/db%2Fovercooked-develop.json?alt=media")
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("overcooked_database", result);
                            editor.apply();
                            model = new GsonBuilder().create().fromJson(result, RecipeResponseDataModel.class);
                            setChanged();
                            notifyObservers();
                        }
                    });
        }

    }

    public RecipeResponseDataModel get() {
        return model;
    }

    /*public RecipeDataModel get(String id) {
        return model == null ? null : model.getRecipes().get(id);
    }*/
}

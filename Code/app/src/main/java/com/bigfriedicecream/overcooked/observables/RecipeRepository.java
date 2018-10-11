package com.bigfriedicecream.overcooked.observables;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bigfriedicecream.overcooked.BuildConfig;
import com.bigfriedicecream.overcooked.models.CondensedRecipeModel;
import com.bigfriedicecream.overcooked.models.RecipeModel;
import com.bigfriedicecream.overcooked.models.RecipeResponseDataModel;
import com.google.gson.GsonBuilder;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;
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
                    .load(BuildConfig.DATABASE_URL)
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

    public List<CondensedRecipeModel> getRecipeList() {
        List<CondensedRecipeModel> recipeList = new ArrayList<>();

        for (RecipeResponseDataModel.RecipeDataModel recipe : model.getRecipes().values()) {
            CondensedRecipeModel recipeModel = new CondensedRecipeModel();
            recipeModel.setId(recipe.getId());
            recipeModel.setTitle(recipe.getTitle());
            recipeModel.setImageURL(BuildConfig.BASE_URL + "/recipes%2F" + recipe.getId() + "%2Fhero.jpg?alt=media");
            recipeList.add(recipeModel);
        }

        return recipeList;
    }

    public RecipeModel getRecipeById(String id) {
        RecipeModel recipeModel = new RecipeModel();
        RecipeResponseDataModel.RecipeDataModel recipe = model.getRecipes().get(id);

        recipeModel.setId(recipe.getId());
        recipeModel.setTitle(recipe.getTitle());
        recipeModel.setImageURL(BuildConfig.BASE_URL + "/recipes%2F" + recipe.getId() + "%2Fhero.jpg?alt=media");
        recipeModel.setServes(recipe.getServes());
        recipeModel.setMakes(recipe.getMakes());
        recipeModel.setPrepTime(recipe.getPrepTime());
        recipeModel.setCookTime(recipe.getCookTime());
        recipeModel.setMethod(recipe.getMethod());

        return recipeModel;
    }
}

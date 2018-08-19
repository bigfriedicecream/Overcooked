package com.bigfriedicecream.recipes.presenters;

import android.content.Context;

import com.bigfriedicecream.recipes.interfaces.IRecipeListContract;
import com.bigfriedicecream.recipes.models.RecipeDataModel;
import com.bigfriedicecream.recipes.models.RecipeModel;
import com.bigfriedicecream.recipes.models.RecipeResponseDataModel;

import java.util.Map;

public class RecipeListPresenter implements IRecipeListContract.Presenter {

    private IRecipeListContract.View view;

    public RecipeListPresenter(IRecipeListContract.View v) {
        view = v;
    }

    public void load(Context context) {
        RecipeResponseDataModel recipeResponseModel = RecipeModel.getList(context);
        Map<String, RecipeDataModel> map = recipeResponseModel.recipes;
        view.render(map);
    }

}

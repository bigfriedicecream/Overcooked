package com.bigfriedicecream.overcooked.observables

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.bigfriedicecream.overcooked.BuildConfig
import com.bigfriedicecream.overcooked.lookups.LookupIngDisplayType
import com.bigfriedicecream.overcooked.models.*
import com.koushikdutta.ion.Ion

import java.util.Observable
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken


class RecipeRepository private constructor() : Observable() {

    private object Holder {
        val INSTANCE = RecipeRepository()
    }

    companion object {
        val instance:RecipeRepository by lazy { Holder.INSTANCE }
        var model:String? = null
        var isFetching:Boolean = false
    }

    fun load(context:Context?) {
        if (model != null) {
            setChanged()
            notifyObservers()
            return
        }

        if (isFetching) {
            return
        }

        isFetching = true

        val sharedPreferences:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val overcookedDb:String = sharedPreferences.getString("overcooked_database", "")

        if (overcookedDb != "") {
            model = overcookedDb
            setChanged()
            notifyObservers()
        }

        Ion
                .with(context)
                .load(BuildConfig.DATABASE_URL)
                .asString()
                .setCallback { _, result ->
                    if (result != null) {
                        val editor:SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("overcooked_database", result)
                        editor.apply()

                        val willChange:Boolean = model == null

                        model = result

                        if (willChange) {
                            setChanged()
                            notifyObservers()
                        }

                        isFetching = false
                    }
                }
    }

    fun getRecipeList():HashMap<String, RecipeModel> {
        val overcooked:JsonObject = Gson().fromJson(model, JsonObject::class.java)
        val recipesJson:JsonObject = overcooked.getAsJsonObject("recipes")

        val listType = object : TypeToken<HashMap<String, RecipeModel>>() { }.type
        val recipeList = GsonBuilder().create().fromJson<HashMap<String, RecipeModel>>(recipesJson, listType)

        for ((_, recipe) in recipeList) {
            recipe.imageURL = "${BuildConfig.BASE_URL}/images%2Frecipes%2F${recipe.id}%2Fhero.jpg?alt=media"
        }

        return recipeList
    }

    fun getRecipeById(id:String?):RecipeModel {
        val overcooked:JsonObject = Gson().fromJson(model, JsonObject::class.java)

        val recipesJson:JsonObject = overcooked.getAsJsonObject("recipes")
        val recipeJson:JsonObject = recipesJson.getAsJsonObject(id)
        val recipeIngredientsJson:JsonArray = recipeJson.getAsJsonArray("ings")

        val recipe:RecipeModel = GsonBuilder().create().fromJson(recipeJson, RecipeModel::class.java)
        recipe.imageURL = "${BuildConfig.BASE_URL}/images%2Frecipes%2F${recipe.id}%2Fhero.jpg?alt=media"

        recipe.ingredients = ArrayList()

        // add recipe ingredients
        for (item in recipeIngredientsJson) {
            val ing = item.asJsonObject
            val ingDisplayTypeId = ing.get("ingDisplayTypeId").asInt

            var recipeIngredient:Any? = null

            when (ingDisplayTypeId) {
                LookupIngDisplayType.Normal.id -> {
                    recipeIngredient = GsonBuilder().create().fromJson(ing, NormalRecipeIngredient::class.java)
                    val ingredientsJson:JsonObject = overcooked.getAsJsonObject("ingredients")
                    val ingredientJson:JsonObject = ingredientsJson.getAsJsonObject(recipeIngredient.ingredientId)
                    val ingredient = GsonBuilder().create().fromJson(ingredientJson, IngredientModel::class.java)
                    recipeIngredient.name = ingredient.name
                    recipeIngredient.namePlural = ingredient.namePlural
                    recipeIngredient.unitTypes = ingredient.unitTypes
                }
                LookupIngDisplayType.Heading.id -> {
                    recipeIngredient = GsonBuilder().create().fromJson(ing, HeadingRecipeIngredient::class.java)
                }
                LookupIngDisplayType.TextOnly.id -> {
                    recipeIngredient = GsonBuilder().create().fromJson(ing, TextOnlyRecipeIngredient::class.java)
                }
            }

            recipe.ingredients.add(recipeIngredient)
        }

        return recipe
    }
}

package com.twobrothers.overcooked.app

import com.google.gson.*
import com.twobrothers.overcooked.models.recipe.Data
import com.twobrothers.overcooked.models.recipe.RecipeDataModel
import org.json.JSONObject
import java.lang.reflect.Type

class RecipeResponseDeserializer: JsonDeserializer<RecipeDataModel> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): RecipeDataModel {
        val jsonObject = json?.asJsonObject

        /* val recipeDataModel = RecipeDataModel()

        recipeDataModel.data = context?.deserialize(jsonObject?.get("data"), Data::class.java)

        return recipeDataModel*/

        val data = jsonObject?.get("data")?.asJsonObject
        val recipe = data?.get("recipe")?.asJsonObject
        val ingredientSections = recipe?.get("ingredientSections")?.asJsonArray

        val ingredientList = JsonArray()

        ingredientSections?.forEach {
            val ingredients = it.asJsonObject.get("ingredients").asJsonArray
            ingredients.forEach {
                val ingredientType = it.asJsonObject.get("ingredientType").asInt
                when (ingredientType) {
                    0 -> ingredientList.add("quantified item")
                    1 -> ingredientList.add("free text")
                }
            }
        }

        recipe?.add("ingredients", ingredientList)

        return RecipeDataModel(
                context?.deserialize(data, Data::class.java)
        )


    }
}
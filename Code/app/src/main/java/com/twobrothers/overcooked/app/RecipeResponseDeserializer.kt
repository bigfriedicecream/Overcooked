package com.twobrothers.overcooked.app

import com.google.gson.*
import com.twobrothers.overcooked.models.recipe.Data
import com.twobrothers.overcooked.models.recipe.FreeText
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
            val heading = it.asJsonObject.get("heading")?.asString
            val ingredients = it.asJsonObject.get("ingredients").asJsonArray

            if (heading != null) {
                ingredientList.add("heading")
            }

            ingredients.forEach {
                val ingredientType = it.asJsonObject.get("ingredientType").asInt
                when (ingredientType) {
                    0 -> ingredientList.add("quantified item")
                    1 -> {
                        val freeText: FreeText? = context?.deserialize(it, FreeText::class.java)
                        ingredientList.add(Gson().toJson(freeText))
                    }
                }
            }
        }

        recipe?.add("ingredients", ingredientList)

        return RecipeDataModel(
                context?.deserialize(data, Data::class.java)
        )


    }
}
package com.twobrothers.overcooked.app

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
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

        val d: JsonObject? = jsonObject?.get("data")?.asJsonObject
        d?.addProperty("happy", "me, i am most happy")

        return RecipeDataModel(
                context?.deserialize(d, Data::class.java)
        )


    }
}
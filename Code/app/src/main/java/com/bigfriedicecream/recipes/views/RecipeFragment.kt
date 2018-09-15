package com.bigfriedicecream.recipes.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bigfriedicecream.recipes.R
import com.bigfriedicecream.recipes.interfaces.IRecipeContract
import com.bigfriedicecream.recipes.models.RecipeDataModel
import com.bigfriedicecream.recipes.presenters.RecipePresenter
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.fragment_recipe.view.*

class RecipeFragment:Fragment(), IRecipeContract.View {

    private var presenter:IRecipeContract.Presenter = RecipePresenter(this)
    private var id:String? = null
    private var isLoaded:Boolean = false

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, state:Bundle?):View? {
        presenter = RecipePresenter(this)

        id = arguments!!.getString("id")

        state?.getBoolean("isLoaded")

        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
        if (!isLoaded) {
            presenter.load(id)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        state.putBoolean("isLoaded", isLoaded)
    }

    override fun render(recipe: RecipeDataModel) {

        val serves = "Serves: ${recipe.serves}"
        val makes = "Makes: ${recipe.makes}"
        val prepTime = "Prep: ${recipe.prepTime} Min"
        val cookTime = "Cook ${recipe.cookTime} Min"
        val totalTime = "${recipe.prepTime + recipe.cookTime} Min"

        val hero:ImageView = view!!.findViewById(R.id.hero)

        Ion.with(hero).load("https://raw.githubusercontent.com/bigfriedicecream/Recipes/develop/Assets/images/${recipe.id}/hero.jpg")

        view!!.title.text = recipe.title
        view!!.serves.text = serves
        view!!.makes.text = makes
        view!!.prep_time.text = prepTime
        view!!.cook_time.text = cookTime
        view!!.total_time.text = totalTime

        // render ingredients
        recipe.ingredients.forEach { ing ->
            val textView = TextView(context)
            textView.text = ing
            view!!.ingredients_container.addView(textView)
        }

        // render method
        view!!.method_container.removeAllViews()
        recipe.method.forEachIndexed { i, method ->
            val bundle = Bundle()
            bundle.putInt("counter", i + 1)
            bundle.putString("step", method)

            val methodItem:Fragment = RecipeMethodItemFragment()
            methodItem.arguments = bundle

            fragmentManager!!
                    .beginTransaction()
                    .add(R.id.method_container, methodItem)
                    .commit()
        }

        isLoaded = true
    }
}

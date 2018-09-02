package com.bigfriedicecream.recipes.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bigfriedicecream.recipes.R
import com.bigfriedicecream.recipes.interfaces.IRecipeContract
import com.bigfriedicecream.recipes.models.RecipeDataModel
import com.bigfriedicecream.recipes.presenters.RecipePresenter
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

        val hero:ImageView = view!!.findViewById(R.id.hero)

        Ion.with(hero).load("https://c7823c74fcf5919154bf-dc9422fbfab3e488dbd72b998b6187ac.ssl.cf4.rackcdn.com/content/3000/2763/loup-emilyskyefit-sweetpotatonachos-veglentilchilli-l.jpg")

        view!!.title.text = recipe.title
        view!!.serves.text = serves
        view!!.makes.text = makes
        view!!.prep_time.text = prepTime
        view!!.cook_time.text = cookTime

        isLoaded = true
    }
}

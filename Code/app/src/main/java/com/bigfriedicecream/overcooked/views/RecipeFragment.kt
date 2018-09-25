package com.bigfriedicecream.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigfriedicecream.overcooked.BuildConfig

import com.bigfriedicecream.overcooked.R
import com.bigfriedicecream.overcooked.interfaces.IRecipeContract
import com.bigfriedicecream.overcooked.models.RecipeDataModel
import com.bigfriedicecream.overcooked.presenters.RecipePresenter
import com.bigfriedicecream.overcooked.utils.GlideApp
import com.bigfriedicecream.overcooked.utils.minsToPrettyTimeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.fragment_recipe.view.*

class RecipeFragment:Fragment(), IRecipeContract.View {

    private var presenter:IRecipeContract.Presenter = RecipePresenter(this)
    private var id:String = ""
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

    override fun onSaveInstanceState(state:Bundle) {
        super.onSaveInstanceState(state)
        state.putBoolean("isLoaded", isLoaded)
    }

    override fun render(recipe:RecipeDataModel) {
        val servesMakes:String = if (recipe.serves > 0) "Serves ${recipe.serves}" else "Makes ${recipe.makes}"
        val prepTime = "Prep ${Int.minsToPrettyTimeFormat(recipe.prepTime)}"
        val cookTime = "Cook ${Int.minsToPrettyTimeFormat(recipe.cookTime)}"
        val totalTime:String = Int.minsToPrettyTimeFormat(recipe.prepTime + recipe.cookTime)

        GlideApp
                .with(view!!)
                .load("${BuildConfig.BASE_URL}/Assets/images/${recipe.id}/hero.jpg")
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(view!!.hero)

        view!!.title.text = recipe.title
        view!!.serves_makes.text = servesMakes
        view!!.prep_time.text = prepTime
        view!!.cook_time.text = cookTime
        view!!.total_time.text = totalTime

        // render ingredients
        view!!.ingredients_container.removeAllViews()
        recipe.ingredients?.forEach { ing ->
            val bundle = Bundle()
            bundle.putString("item", ing)

            val ingredientItem:Fragment = RecipeIngredientItemFragment()
            ingredientItem.arguments = bundle

            fragmentManager!!
                    .beginTransaction()
                    .add(R.id.ingredients_container, ingredientItem)
                    .commit()
        }

        // render method
        view!!.method_container.removeAllViews()
        recipe.method?.forEachIndexed { i, method ->
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

package com.bigfriedicecream.overcooked.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigfriedicecream.overcooked.BuildConfig

import com.bigfriedicecream.overcooked.R
import com.bigfriedicecream.overcooked.interfaces.IRecipeContract
import com.bigfriedicecream.overcooked.models.HeadingRecipeIngredient
import com.bigfriedicecream.overcooked.models.NormalRecipeIngredient
import com.bigfriedicecream.overcooked.models.RecipeModel
import com.bigfriedicecream.overcooked.models.TextOnlyRecipeIngredient
import com.bigfriedicecream.overcooked.presenters.RecipePresenter
import com.bigfriedicecream.overcooked.utils.GlideApp
import com.bigfriedicecream.overcooked.utils.minsToPrettyTimeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import kotlinx.android.synthetic.main.dialog_adjust_quantity.view.*
import kotlinx.android.synthetic.main.fragment_recipe.view.*

class RecipeFragment:Fragment(), IRecipeContract.View {

    private var presenter:IRecipeContract.Presenter = RecipePresenter(this)
    private var id:String? = ""

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, state:Bundle?):View? {
        presenter = RecipePresenter(this)
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        id = arguments?.getString("id")

        view.btn_adjust_quantity?.setOnClickListener { presenter.adjustQuantityClick() }

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.start(context, id)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun render(recipe:RecipeModel?) {
        if (recipe == null) {
            return
        }

        val servesMakes:String = if (recipe.serves > 0) recipe.serves.toString() else recipe.makes.toString()
        val servesMakesHeading:String = if (recipe.serves > 0) "Serves" else "Makes"

        val prepTime = Int.minsToPrettyTimeFormat(recipe.prepTime)
        val cookTime = Int.minsToPrettyTimeFormat(recipe.cookTime)

        view?.serves_makes_heading?.text = servesMakesHeading
        view?.serves_makes?.text = servesMakes
        view?.prep_time?.text = prepTime
        view?.cook_time?.text = cookTime

        view?.title?.text = recipe.title

        GlideApp
                .with(view!!)
                .load(recipe.imageURL)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(view!!.hero)

        // render ingredients
        view?.ingredients_container?.removeAllViews()
        recipe.ingredients.forEach {
            var fragment:Fragment? = null

            when (it) {
                is NormalRecipeIngredient -> {
                    fragment = NormalIngredientItemFragment()
                }
                is HeadingRecipeIngredient -> {
                    fragment = HeadingIngredientItemFragment()
                }
                is TextOnlyRecipeIngredient -> {
                    fragment = TextOnlyIngredientItemFragment()
                }
            }

            if (fragment != null) {
                val bundle = Bundle()
                bundle.putString("data", Gson().toJson(it))
                fragment.arguments = bundle
                fragmentManager!!
                        .beginTransaction()
                        .add(R.id.ingredients_container, fragment)
                        .commitAllowingStateLoss()
            }
        }

        // render method
        view?.method_container?.removeAllViews()
        recipe.method.forEachIndexed { i, method ->
            val bundle = Bundle()
            bundle.putInt("counter", i + 1)
            bundle.putString("step", method)

            val methodItem:Fragment = RecipeMethodItemFragment()
            methodItem.arguments = bundle

            fragmentManager!!
                    .beginTransaction()
                    .add(R.id.method_container, methodItem)
                    .commitAllowingStateLoss()
        }

    }

    override fun showAdjustQuantityDialog(recipe:RecipeModel?) {
        if (recipe == null) {
            return
        }

        val builder = AlertDialog.Builder(activity)
        val dialog = layoutInflater.inflate(R.layout.dialog_adjust_quantity, null)

        val recipeQuantity = if (recipe.serves > 0) recipe.serves else recipe.makes

        dialog.quantity.minValue = recipeQuantity / 2
        dialog.quantity.maxValue = recipeQuantity * 2
        dialog.quantity.value = recipeQuantity
        dialog.quantity.wrapSelectorWheel = false

        builder
                .setTitle("Adjust Quantity")
                .setView(dialog)
                // Add action buttons
                .setPositiveButton("Confirm") { _, _ ->
                    presenter.updateQuantity(dialog.quantity.value)
                }
                .setNegativeButton("Cancel") { d, _ ->
                    d.cancel()
                }
        builder.create().show()
    }
}

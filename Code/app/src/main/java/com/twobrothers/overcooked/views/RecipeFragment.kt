package com.twobrothers.overcooked.views

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.models.HeadingRecipeIngredient
import com.twobrothers.overcooked.models.NormalRecipeIngredient
import com.twobrothers.overcooked.models.RecipeModel
import com.twobrothers.overcooked.models.TextOnlyRecipeIngredient
import com.twobrothers.overcooked.presenters.RecipePresenter
import com.twobrothers.overcooked.utils.GlideApp
import com.twobrothers.overcooked.utils.minsToPrettyTimeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import kotlinx.android.synthetic.main.dialog_adjust_quantity.view.*
import kotlinx.android.synthetic.main.fragment_recipe.view.*

class RecipeFragment:Fragment(), IRecipeContract.View {

    private var presenter:IRecipeContract.Presenter = RecipePresenter(this)

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, state:Bundle?):View? {
        presenter = RecipePresenter(this)
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        view.btn_adjust_quantity?.setOnClickListener { presenter.adjustQuantityClick() }
        view.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        return view
    }

    override fun onStart() {
        super.onStart()
        val id = arguments?.getString("id")
        val restore = arguments?.getBoolean("restore", false)
        presenter.start(context, id, restore)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
        arguments?.putBoolean("restore", true)
    }

    override fun render(recipe:RecipeModel?) {
        if (recipe == null) {
            return
        }

        val totalServes:Int = (recipe.serves * recipe.multiplier).toInt()
        val totalMakes:Int = (recipe.makes * recipe.multiplier).toInt()

        val servesMakesUnit:Int = if (recipe.serves > 0) totalServes else totalMakes
        val servesMakesHeading:String = if (recipe.serves > 0) "Serves" else "Makes"

        val prepTime = Int.minsToPrettyTimeFormat(recipe.prepTime)
        val cookTime = Int.minsToPrettyTimeFormat(recipe.cookTime)

        view?.serves_makes_heading?.text = servesMakesHeading
        view?.serves_makes?.text = servesMakesUnit.toString()
        view?.prep_time?.text = prepTime
        view?.cook_time?.text = cookTime

        view?.title?.text = recipe.title

        view?.txt_adjustment_note?.visibility = View.GONE

        if (recipe.serves != totalServes || recipe.makes != totalMakes) {
            val serveMakeString = if (recipe.serves > 0) "serve" else "make"
            view?.txt_adjustment_note?.text = "Cook time and prep time may vary to $serveMakeString $servesMakesUnit"
            view?.txt_adjustment_note?.visibility = View.VISIBLE
        }

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
                bundle.putDouble("multiplier", recipe.multiplier)
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
        dialog.quantity.value = (recipeQuantity * recipe.multiplier).toInt()
        dialog.quantity.wrapSelectorWheel = false

        builder
                .setTitle("Adjust Quantity")
                .setView(dialog)
                .setPositiveButton("Confirm") { _, _ ->
                    presenter.updateQuantity(dialog.quantity.value, context)
                }
                .setNegativeButton("Cancel") { d, _ ->
                    d.cancel()
                }
                .setNeutralButton("Reset") { _, _ ->
                    presenter.updateQuantity(recipeQuantity, context)
                }
        builder.create().show()
    }
}

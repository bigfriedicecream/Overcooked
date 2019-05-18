package com.twobrothers.overcooked.views.recipe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.jakewharton.rxbinding.view.RxView
import com.twobrothers.overcooked.BuildConfig

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.app.AppState
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.presenters.RecipePresenter
import com.twobrothers.overcooked.utils.GlideApp
import com.twobrothers.overcooked.utils.toFriendlyTimeFormat
import kotlinx.android.synthetic.main.fragment_recipe.*
import kotlinx.android.synthetic.main.fragment_recipe.view.*

class RecipeFragment : Fragment(), IRecipeContract.View {

    private val presenter: IRecipeContract.Presenter = RecipePresenter(this)
    private lateinit var methodViewAdapter: RecyclerView.Adapter<*>
    private lateinit var methodViewManager: RecyclerView.LayoutManager
    private lateinit var ingredientViewAdapter: RecyclerView.Adapter<*>
    private lateinit var ingredientViewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        if (savedInstanceState == null) {
            presenter.reset()
        }

        methodViewManager = LinearLayoutManager(context)
        methodViewAdapter = MethodViewAdapter(presenter)

        view.findViewById<RecyclerView>(R.id.recycler_method).apply {
            layoutManager = methodViewManager
            adapter = methodViewAdapter
        }

        ingredientViewManager = LinearLayoutManager(context)
        ingredientViewAdapter = IngredientViewAdapter(presenter)

        view.findViewById<RecyclerView>(R.id.recycler_ingredients).apply {
            layoutManager = ingredientViewManager
            adapter = ingredientViewAdapter
        }

        RxView.clicks(view.btn_increment).subscribe {
            presenter.onIncrementQuantity()
        }

        RxView.clicks(view.btn_decrement).subscribe {
            presenter.onDecrementQuantity()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart(this.arguments)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun render(recipe: RecipeModel, activeQuantity: Int) {
        if (isDetached) {
            return
        }

        text_title.text = recipe.title
        text_quantity.text = activeQuantity.toString()
        text_serves_makes.text = if (recipe.serves != null) "Serves" else "Makes"

        text_preptime.text = Int.toFriendlyTimeFormat(recipe.prepTime)
        text_cooktime.text = Int.toFriendlyTimeFormat(recipe.cookTime)

        GlideApp.with(view!!.context)
                .load("${BuildConfig.IMAGE_ENDPOINT}${recipe.imageUrl}")
                .signature(ObjectKey("${recipe.id}${recipe.lastUpdated}"))
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(image_hero)

        btn_increment.isEnabled = true
        btn_increment.alpha = 1.toFloat()
        btn_decrement.isEnabled = true
        btn_decrement.alpha = 1.toFloat()

        if (activeQuantity >= AppState.Recipe.MAX_SERVES_MAKES) {
            btn_increment.isEnabled = false
            btn_increment.alpha = 0.2.toFloat()
        }

        if (activeQuantity <= AppState.Recipe.MIN_SERVES_MAKES) {
            btn_decrement.isEnabled = false
            btn_decrement.alpha = 0.2.toFloat()
        }

        progress_loading.visibility = View.GONE
        view_content.visibility = View.VISIBLE
    }

    override fun onMethodDataSetChanged() {
        methodViewAdapter.notifyDataSetChanged()
    }

    override fun onIngredientDataSetChanged() {
        ingredientViewAdapter.notifyDataSetChanged()
    }

}

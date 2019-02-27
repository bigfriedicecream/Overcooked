package com.twobrothers.overcooked.views.recipe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.presenters.RecipePresenter
import com.twobrothers.overcooked.utils.GlideApp
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment(), IRecipeContract.View {

    private val presenter: IRecipeContract.Presenter = RecipePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun render(recipe: RecipeModel) {
        if (isDetached) {
            return
        }
        text_title.text = recipe.title
        GlideApp.with(view!!.context)
                .load(recipe.imageUrl)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(image_hero)
    }

}

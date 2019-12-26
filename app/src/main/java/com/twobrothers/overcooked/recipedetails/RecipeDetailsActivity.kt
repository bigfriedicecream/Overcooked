package com.twobrothers.overcooked.recipedetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.databinding.ActivityRecipeDetailsBinding
import com.twobrothers.overcooked.recipedetails.models.FreeTextIngredient
import com.twobrothers.overcooked.recipedetails.models.HeadingIngredient
import com.twobrothers.overcooked.recipedetails.models.QuantifiedIngredient
import com.twobrothers.overcooked.recipedetails.presentation.MethodStepView
import com.twobrothers.overcooked.recipedetails.presentation.getQuantifiedIngredientReadableFormat
import kotlinx.android.synthetic.main.activity_recipe_details.*

class RecipeDetailsActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_RECIPE_ID = "RecipeDetailsActivity.Extras.id"

        @JvmStatic
        fun newIntent(launchContext: Context, id: String): Intent {
            val intent = Intent(launchContext, RecipeDetailsActivity::class.java)
            intent.putExtra(EXTRA_RECIPE_ID, id)
            return intent
        }

    }

    private lateinit var viewModel: RecipeDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        // Init view model
        val id = this.intent.getStringExtra(EXTRA_RECIPE_ID) ?: ""
        viewModel = RecipeDetailsViewModel(id)

        // Init data binding
        DataBindingUtil.setContentView<ActivityRecipeDetailsBinding>(
            this, R.layout.activity_recipe_details
        ).apply {
            this.lifecycleOwner = this@RecipeDetailsActivity
            this.viewModel = this@RecipeDetailsActivity.viewModel
        }

        viewModel.heroImageUrl.observe(this, Observer {
            Glide
                .with(this)
                .load(it)
                .centerCrop()
                .placeholder(R.drawable.img_placeholder_16_9)
                .into(image_hero)
        })

        // Init view model observers
        viewModel.ingredients.observe(this, Observer {
            layout_ingredients.removeAllViews()
            val serves = it.first
            it.second.forEach {
                val view = when (it) {
                    is HeadingIngredient -> {
                        val view = layoutInflater.inflate(
                            R.layout.view_ingredient_heading,
                            layout_ingredients,
                            false
                        )
                        view.findViewById<TextView>(R.id.text_title).text = it.title
                        view
                    }
                    is QuantifiedIngredient -> {
                        val view = layoutInflater.inflate(
                            R.layout.view_ingredient_default,
                            layout_ingredients,
                            false
                        )
                        view.findViewById<TextView>(R.id.text_description).text =
                            getQuantifiedIngredientReadableFormat(this, it, serves)
                        view
                    }
                    is FreeTextIngredient -> {
                        val view = layoutInflater.inflate(
                            R.layout.view_ingredient_default,
                            layout_ingredients,
                            false
                        )
                        view.findViewById<TextView>(R.id.text_description).text = it.description
                        view
                    }
                }
                layout_ingredients.addView(view)
            }
        })

        viewModel.method.observe(this, Observer {
            layout_method.removeAllViews()
            it.forEachIndexed { i, step ->
                val view = MethodStepView(this)
                view.setContent(i + 1, step)
                layout_method.addView(view)
            }
        })
    }
}

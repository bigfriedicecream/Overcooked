package com.twobrothers.overcooked.recipedetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.databinding.ActivityRecipeDetailsBinding
import com.twobrothers.overcooked.recipedetails.models.FreeTextIngredient
import com.twobrothers.overcooked.recipedetails.models.HeadingIngredient
import com.twobrothers.overcooked.recipedetails.models.QuantifiedIngredient
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

        // Init view model observers
        viewModel.ingredients.observe(this, Observer {
            layout_ingredients.removeAllViews()
            it.forEach {
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
                        val view = TextView(this)
                        // TODO: Serves is hardcoded
                        view.text = getQuantifiedIngredientReadableFormat(this, it, 2)
                        view
                    }
                    is FreeTextIngredient -> {
                        val view = TextView(this)
                        view.text = it.description
                        view
                    }
                }
                layout_ingredients.addView(view)
            }
        })

        viewModel.method.observe(this, Observer {
            layout_method.removeAllViews()
            it.forEachIndexed { i, step ->
                val stepView = TextView(this)
                val stepText = "${i + 1} $step"
                stepView.text = stepText
                layout_method.addView(stepView)
            }
        })
    }
}

package com.twobrothers.overcooked.recipedetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.databinding.ActivityRecipeDetailsBinding
import kotlinx.android.synthetic.main.activity_recipe_library.*

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
    }
}

package com.twobrothers.overcooked.recipelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.core.framework.viewModelFactory
import com.twobrothers.overcooked.databinding.ActivityRecipeLibraryBinding
import com.twobrothers.overcooked.recipedetails.RecipeDetailsActivity
import kotlinx.android.synthetic.main.activity_recipe_library.*
import kotlinx.android.synthetic.main.include_generic_error.*

class RecipeLibraryActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeLibraryViewModel
    private lateinit var listAdapter: RecipeLibraryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_library)

        // Init view model
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory { RecipeLibraryViewModel() }
        ).get(RecipeLibraryViewModel::class.java)

        // Init data binding
        DataBindingUtil.setContentView<ActivityRecipeLibraryBinding>(
            this, R.layout.activity_recipe_library
        ).apply {
            this.lifecycleOwner = this@RecipeLibraryActivity
            this.viewModel = this@RecipeLibraryActivity.viewModel
        }

        // Init list adapter
        listAdapter = RecipeLibraryListAdapter(object : OnRecipeClickListener {
            override fun onClick(id: String) {
                viewModel.onRecipeClick(id)
            }
        })
        recycler_score_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        // Init view model observers
        viewModel.recipes.observe(this, Observer {
            listAdapter.submitList(it)
        })

        viewModel.navigateToRecipeDetails.observe(this, Observer {
            it.getContentIfNotHandled()?.let { id ->
                startActivity(RecipeDetailsActivity.newIntent(this, id))
            }
        })

        // Init listeners
        button_generic_retry.setOnClickListener {
            viewModel.onRetryClick()
        }

    }
}

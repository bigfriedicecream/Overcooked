package com.twobrothers.overcooked.recipelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.twobrothers.overcooked.R
import kotlinx.android.synthetic.main.activity_recipe_library.*

class RecipeLibraryActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeLibraryViewModel
    private lateinit var listAdapter: RecipeLibraryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_library)

        // Init view model
        viewModel = RecipeLibraryViewModel()

        // Init list adapter
        listAdapter = RecipeLibraryListAdapter()
        recycler_score_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        // Init view model observers
        viewModel.recipes.observe(this, Observer {
            listAdapter.submitList(it)
        })

    }
}

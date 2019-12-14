package com.twobrothers.overcooked.recipelibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twobrothers.overcooked.R

class RecipeLibraryActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeLibraryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_library)

        // Init view model
        viewModel = RecipeLibraryViewModel()
    }
}

package com.twobrothers.overcooked.interactive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.core.framework.viewModelFactory
import com.twobrothers.overcooked.databinding.ActivityInteractiveBinding
import kotlinx.android.synthetic.main.activity_interactive.*


class InteractiveActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_RECIPE_ID = "RecipeDetailsActivity.Extras.id"

        @JvmStatic
        fun newIntent(launchContext: Context, id: String): Intent {
            val intent = Intent(launchContext, InteractiveActivity::class.java)
            intent.putExtra(EXTRA_RECIPE_ID, id)
            return intent
        }

    }

    private lateinit var viewModel: InteractiveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        // Init view model
        val id = this.intent.getStringExtra(EXTRA_RECIPE_ID) ?: ""
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory {
                InteractiveViewModel(
                    id
                )
            }
        ).get(InteractiveViewModel::class.java)

        // Init data binding
        DataBindingUtil.setContentView<ActivityInteractiveBinding>(
            this, R.layout.activity_interactive
        ).apply {
            this.lifecycleOwner = this@InteractiveActivity
            this.viewModel = this@InteractiveActivity.viewModel
        }

        // Init view model observers
        viewModel.interactive.observe(this, Observer {
            view_pager_interactive.adapter = InteractiveAdapter(supportFragmentManager, it)
        })
    }
}

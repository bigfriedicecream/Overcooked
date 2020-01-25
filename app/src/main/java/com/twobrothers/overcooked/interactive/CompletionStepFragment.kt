package com.twobrothers.overcooked.interactive

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.core.framework.viewModelFactory
import com.twobrothers.overcooked.databinding.FragmentCompletionStepBinding
import com.twobrothers.overcooked.recipedetails.models.*
import kotlinx.android.synthetic.main.fragment_completion_step.*

class CompletionStepFragment : Fragment() {

    companion object {

        private const val ARGUMENT_COMPLETION_STEP =
            "InteractiveStepFragment.Argument.CompletionStep"

        @JvmStatic
        fun newInstance(completionStep: CompletionStep): CompletionStepFragment {
            val fragment = CompletionStepFragment()
            val arguments = Bundle()
            arguments.putSerializable(ARGUMENT_COMPLETION_STEP, completionStep)
            fragment.arguments = arguments
            return fragment
        }

    }

    private lateinit var viewModel: CompletionStepViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Init ViewModel
        val completionStep = arguments?.get(ARGUMENT_COMPLETION_STEP) as CompletionStep
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory {
                CompletionStepViewModel(completionStep)
            }
        ).get(CompletionStepViewModel::class.java)

        // Init Data Binding
        val binding = FragmentCompletionStepBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Init view model observers
        viewModel.heroImageUrl.observe(this, Observer {
            Glide
                .with(layout_root)
                .load(it)
                .placeholder(R.drawable.img_placeholder_4_3)
                .apply(RequestOptions.circleCropTransform())
                .into(image_hero)
        })

        return binding.root
    }

}
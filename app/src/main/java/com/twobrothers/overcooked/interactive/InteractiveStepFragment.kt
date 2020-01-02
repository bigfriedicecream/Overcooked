package com.twobrothers.overcooked.interactive

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.core.framework.viewModelFactory
import com.twobrothers.overcooked.databinding.ActivityInteractiveBinding
import com.twobrothers.overcooked.databinding.FragmentInteractiveStepBinding
import com.twobrothers.overcooked.recipedetails.models.InteractiveStep
import javax.inject.Inject

class InteractiveStepFragment : Fragment() {

    companion object {

        private const val ARGUMENT_INTERACTIVE_STEP = "InteractiveStepFragment.Argument.Step"

        @JvmStatic
        fun newInstance(step: InteractiveStep): InteractiveStepFragment {
            val fragment = InteractiveStepFragment()
            val arguments = Bundle()
            arguments.putSerializable(ARGUMENT_INTERACTIVE_STEP, step)
            fragment.arguments = arguments
            return fragment
        }

    }

    private lateinit var viewModel: InteractiveStepViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Init ViewModel
        val step = arguments?.get(ARGUMENT_INTERACTIVE_STEP) as InteractiveStep
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory {
                InteractiveStepViewModel(
                    step
                )
            }
        ).get(InteractiveStepViewModel::class.java)

        // Init Data Binding
        /* val view = DataBindingUtil.setContentView<FragmentInteractiveStepBinding>(
            this, R.layout.activity_interactive
        ).apply {
            this.lifecycleOwner = this@InteractiveStepFragment
            this.viewModel = this@InteractiveStepFragment.viewModel
        }*/
        val binding = FragmentInteractiveStepBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}
package com.twobrothers.overcooked.interactive

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.twobrothers.overcooked.recipedetails.models.CompletionStep
import com.twobrothers.overcooked.recipedetails.models.InteractiveComponent
import com.twobrothers.overcooked.recipedetails.models.InteractiveStep

class InteractiveAdapter(
    fragmentManager: FragmentManager,
    private val interactiveSteps: List<InteractiveComponent>,
    private val serves: Int
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        return when (val component = interactiveSteps[position]) {
            is InteractiveStep -> InteractiveStepFragment.newInstance(component, serves)
            is CompletionStep -> CompletionStepFragment.newInstance(component)
        }
    }

    override fun getCount(): Int {
        return interactiveSteps.count()
    }
}
package com.twobrothers.overcooked.interactive

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.twobrothers.overcooked.recipedetails.models.InteractiveStep

class InteractiveAdapter(
    fragmentManager: FragmentManager,
    private val interactiveSteps: List<InteractiveStep>
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        return InteractiveStepFragment.newInstance(interactiveSteps[position])
    }

    override fun getCount(): Int {
        return interactiveSteps.count()
    }
}
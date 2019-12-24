package com.twobrothers.overcooked.recipedetails.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.twobrothers.overcooked.R
import kotlinx.android.synthetic.main.view_method_step.view.*


class MethodStepView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(
            R.layout.view_method_step, this, true
        )
    }

    fun setContent(step: Int, text: String) {
        text_step.text = step.toString()
        text_details.text = text
    }

}
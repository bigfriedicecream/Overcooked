package com.twobrothers.overcooked.interactive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twobrothers.overcooked.recipedetails.models.InteractiveStep

class InteractiveStepViewModel(private val step: InteractiveStep) : ViewModel() {

    //region state properties

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    //endregion

    init {
        _title.value = step.title
    }

    //region private


    //endregion

}
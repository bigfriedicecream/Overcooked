package com.twobrothers.overcooked.interactive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twobrothers.overcooked.recipedetails.models.InteractiveStep

class InteractiveStepViewModel(step: InteractiveStep) : ViewModel() {

    //region state properties

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _body = MutableLiveData<String>()
    val body: LiveData<String> = _body

    //endregion

    init {
        _title.value = step.title
        _body.value = step.body
    }

    //region private


    //endregion

}
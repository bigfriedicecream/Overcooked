package com.twobrothers.overcooked.interactive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twobrothers.overcooked.recipedetails.models.CompletionStep

class CompletionStepViewModel(completionStep: CompletionStep) : ViewModel() {

    //region state properties

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _heroImageUrl = MutableLiveData<String>()
    val heroImageUrl: LiveData<String> = _heroImageUrl

    //endregion

    init {
        _title.value = completionStep.title
        _heroImageUrl.value = completionStep.heroImageUrl
    }

}
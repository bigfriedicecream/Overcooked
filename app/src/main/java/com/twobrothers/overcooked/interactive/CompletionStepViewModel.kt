package com.twobrothers.overcooked.interactive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twobrothers.overcooked.core.framework.Event
import com.twobrothers.overcooked.recipedetails.models.CompletionStep

class CompletionStepViewModel(completionStep: CompletionStep) : ViewModel() {

    //region state properties

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _heroImageUrl = MutableLiveData<String>()
    val heroImageUrl: LiveData<String> = _heroImageUrl

    //endregion

    //region event properties

    private val _onExitInteractive = MutableLiveData<Event<Unit>>()
    val onExitInteractive: LiveData<Event<Unit>> = _onExitInteractive

    //endregion

    init {
        _title.value = completionStep.title
        _heroImageUrl.value = completionStep.heroImageUrl
    }

    //region public

    fun onCompleteClick() {
        _onExitInteractive.value = Event(Unit)
    }

    //endregion

}
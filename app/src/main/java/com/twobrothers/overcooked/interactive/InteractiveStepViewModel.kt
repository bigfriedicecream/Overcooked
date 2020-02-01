package com.twobrothers.overcooked.interactive

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twobrothers.overcooked.recipedetails.models.Ingredient
import com.twobrothers.overcooked.recipedetails.models.InteractiveStep

class InteractiveStepViewModel(step: InteractiveStep, serves: Int) : ViewModel() {

    //region state properties

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _body = MutableLiveData<String>()
    val body: LiveData<String> = _body

    private val _ingredients = MutableLiveData<Pair<List<Ingredient>?, Int>>()
    val ingredients: LiveData<Pair<List<Ingredient>?, Int>> = _ingredients

    private val _footnote = MutableLiveData<String>()
    val footnote: LiveData<String> = _footnote

    private val _timerDisplay = MutableLiveData<String>()
    val timerDisplay: LiveData<String> = _timerDisplay

    //endregion

    private var timer: CountDownTimer = object : CountDownTimer(0, 0) {
        override fun onFinish() {}
        override fun onTick(millisUntilFinished: Long) {}
    }

    init {
        _title.value = step.title
        _body.value = step.body
        _ingredients.value = Pair(step.ingredients, serves)
        _footnote.value = step.footnote

        if (step.timer != null) {
            setTimer(5000)
            updateTimerDisplay(5000)
        }
    }

    // region private

    private fun setTimer(milliseconds: Long) {
        timer = object : CountDownTimer(milliseconds, 1000) {
            override fun onFinish() {}
            override fun onTick(millisUntilFinished: Long) {
                updateTimerDisplay(millisUntilFinished)
            }
        }
    }

    private fun updateTimerDisplay(millisUntilFinished: Long) {
        _timerDisplay.value = (millisUntilFinished / 1000).toString()
    }

    // endregion

    //region public

    fun startTimer() {
        timer.start()
    }

    //endregion

}
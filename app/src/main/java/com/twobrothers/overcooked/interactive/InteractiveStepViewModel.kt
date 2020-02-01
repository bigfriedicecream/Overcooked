package com.twobrothers.overcooked.interactive

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twobrothers.overcooked.core.framework.Event
import com.twobrothers.overcooked.recipedetails.models.Ingredient
import com.twobrothers.overcooked.recipedetails.models.InteractiveStep
import org.threeten.bp.Duration
import java.util.*
import kotlin.concurrent.fixedRateTimer

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

    // region event properties

    private val _showNotification = MutableLiveData<Event<String>>()
    val showNotification: LiveData<Event<String>> = _showNotification

    //endregion

    private var timer = Timer()
    private var handler = Handler()
    private var timerDurationMs: Duration = Duration.ZERO
    private var timeRemainingMs: Duration = Duration.ZERO

    init {
        _title.value = step.title
        _body.value = step.body
        _ingredients.value = Pair(step.ingredients, serves)
        _footnote.value = step.footnote

        if (step.timer != null) {
            timerDurationMs = Duration.ofMillis(50000) // step.timer
            timeRemainingMs = timerDurationMs
            refreshTimerDisplay()
        }
    }

    // region private

    private fun refreshTimerDisplay() {
        _timerDisplay.value = timeRemainingMs.seconds.toString()
    }

    private fun refreshNotification() {
        _showNotification.value = Event(timeRemainingMs.seconds.toString())
    }

    // endregion

    //region public

    fun startTimer() {
        resetTimer()
        timer = fixedRateTimer("timer", false, 1000, 1000) {
            handler.post {
                timeRemainingMs = timeRemainingMs.minusMillis(1000)
                refreshTimerDisplay()
                refreshNotification()
            }
        }
    }

    fun resetTimer() {
        timer.cancel()
        timer = Timer()
        timeRemainingMs = timerDurationMs
        refreshTimerDisplay()
        refreshNotification()
    }

    //endregion

}
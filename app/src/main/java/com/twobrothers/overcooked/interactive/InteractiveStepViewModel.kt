package com.twobrothers.overcooked.interactive

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

    //endregion

    init {
        _title.value = step.title
        _body.value = step.body
        _ingredients.value = Pair(step.ingredients, serves)
        _footnote.value = step.footnote
    }

    //region private


    //endregion

}
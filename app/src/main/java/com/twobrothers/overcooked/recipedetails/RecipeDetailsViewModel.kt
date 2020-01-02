package com.twobrothers.overcooked.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twobrothers.overcooked.core.framework.Event
import com.twobrothers.overcooked.core.datasource.FirebaseApiDataSource
import com.twobrothers.overcooked.core.dagger.DaggerFirebaseApiDataSourceComponent
import com.twobrothers.overcooked.recipedetails.models.Ingredient
import com.twobrothers.overcooked.recipedetails.models.Recipe
import kotlinx.coroutines.launch
import org.threeten.bp.Duration

class RecipeDetailsViewModel(private val id: String) : ViewModel() {

    private val dataSource: FirebaseApiDataSource =
        DaggerFirebaseApiDataSourceComponent.create().getDataSource()

    private var referenceUrl: String = ""

    //region state properties

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _heroImageUrl = MutableLiveData<String>()
    val heroImageUrl: LiveData<String> = _heroImageUrl

    private val _serves = MutableLiveData<String>()
    val serves: LiveData<String> = _serves

    private val _prepTime = MutableLiveData<Duration>()
    val prepTime: LiveData<Duration> = _prepTime

    private val _cookTime = MutableLiveData<Duration>()
    val cookTime: LiveData<Duration> = _cookTime

    private val _ingredients = MutableLiveData<Pair<Int, List<Ingredient>>>()
    val ingredients: LiveData<Pair<Int, List<Ingredient>>> = _ingredients

    private val _method = MutableLiveData<List<String>>()
    val method: LiveData<List<String>> = _method

    private val _isReferenceVisible = MutableLiveData<Boolean>()
    val isReferenceVisible: LiveData<Boolean> = _isReferenceVisible

    private val _referenceName = MutableLiveData<String>()
    val referenceName: LiveData<String> = _referenceName

    private val _isRecipeDetailsVisible = MutableLiveData<Boolean>()
    val isRecipeDetialsVisible: LiveData<Boolean> = _isRecipeDetailsVisible

    private val _isInteractiveVisible = MutableLiveData<Boolean>()
    val isInteractiveVisible: LiveData<Boolean> = _isInteractiveVisible

    private val _isLoadingIndicatorVisible = MutableLiveData<Boolean>()
    val isLoadingIndicatorVisible: LiveData<Boolean> = _isLoadingIndicatorVisible

    //endregion

    //region event properties

    private val _navigateToReference = MutableLiveData<Event<String>>()
    val navigateToReference: LiveData<Event<String>> = _navigateToReference

    private val _navigateToInteractive = MutableLiveData<Event<String>>()
    val navigateToInteractive: LiveData<Event<String>> = _navigateToInteractive

    //endregion

    init {
        showLoadingIndicator()
        viewModelScope.launch {
            val recipeList = dataSource.getRecipe(id)
            if (recipeList != null) {
                handleSuccess(recipeList)
                showRecipeDetails()
            }
        }
    }

    //region private

    private fun handleSuccess(recipe: Recipe) {
        _title.value = recipe.title
        _heroImageUrl.value = recipe.heroImageUrl
        _serves.value = recipe.serves.toString()
        _prepTime.value = Duration.ofMinutes(recipe.prepTime.toLong())
        _cookTime.value = Duration.ofMinutes(recipe.cookTime.toLong())
        _ingredients.value = Pair(recipe.serves, recipe.ingredients)
        _method.value = recipe.method
        _referenceName.value = recipe.referenceName
        referenceUrl = recipe.referenceUrl
        _isReferenceVisible.value = recipe.referenceName.isNotBlank()
        _isInteractiveVisible.value = recipe.interactive != null
    }

    private fun showLoadingIndicator() {
        _isRecipeDetailsVisible.value = false
        _isLoadingIndicatorVisible.value = true
    }

    private fun showRecipeDetails() {
        _isLoadingIndicatorVisible.value = false
        _isRecipeDetailsVisible.value = true
    }

    //endregion

    //region public

    fun onReferenceClick() {
        _navigateToReference.value = Event(referenceUrl)
    }

    fun onInteractiveClick() {
        _navigateToInteractive.value = Event(id)
    }

    //endregion

}
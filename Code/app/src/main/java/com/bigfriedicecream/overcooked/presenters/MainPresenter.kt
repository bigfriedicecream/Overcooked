package com.bigfriedicecream.overcooked.presenters


import android.content.Context

import com.bigfriedicecream.overcooked.interfaces.IMainContract
import com.bigfriedicecream.overcooked.observables.EventsDispatcher
import com.bigfriedicecream.overcooked.observables.RecipeRepository

import java.util.HashMap
import java.util.Observable
import java.util.Observer

class MainPresenter(private val view: IMainContract.View) : IMainContract.Presenter, Observer {
    private val recipeRepository:RecipeRepository = RecipeRepository.instance
    private val eventsDispatcher:EventsDispatcher = EventsDispatcher.instance

    override fun start(c:Context) {
        eventsDispatcher.addObserver(this)
        recipeRepository.load(c)
    }

    override fun stop(c:Context) {
        eventsDispatcher.deleteObserver(this)
    }

    override fun load() {
        view.renderList()
    }

    override fun update(observable: Observable, o: Any) {
        val hashMap:HashMap<String, String> = o as HashMap<String, String>
        if (observable is EventsDispatcher) {
            if (hashMap["name"] == "NAVIGATION_RECIPE") {
                val id:String = hashMap["id"]!!
                view.renderRecipe(id)
            }
        }
    }
}

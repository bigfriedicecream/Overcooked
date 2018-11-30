package com.twobrothers.overcooked.presenters

import com.twobrothers.overcooked.interfaces.IMainContract
import com.twobrothers.overcooked.observables.EventsDispatcher

import java.util.HashMap
import java.util.Observable
import java.util.Observer


class MainPresenter(private val view: IMainContract.View) : IMainContract.Presenter, Observer {
    private val eventsDispatcher:EventsDispatcher = EventsDispatcher.instance

    override fun start() {
        eventsDispatcher.addObserver(this)
    }

    override fun stop() {
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

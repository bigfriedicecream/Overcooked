package com.twobrothers.overcooked.observables

import java.util.HashMap
import java.util.Observable

class EventsDispatcher private constructor() : Observable() {

    private object Holder {
        val INSTANCE = EventsDispatcher()
    }

    companion object {
        val instance:EventsDispatcher by lazy { Holder.INSTANCE }
    }

    fun dispatch(name: String, id: String) {
        val hashMap = HashMap<String, String>()
        hashMap["name"] = name
        hashMap["id"] = id
        setChanged()
        notifyObservers(hashMap)
    }

}

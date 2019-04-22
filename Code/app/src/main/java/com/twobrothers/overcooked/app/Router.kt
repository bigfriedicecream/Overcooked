package com.twobrothers.overcooked.app

import android.os.Bundle
import io.reactivex.subjects.PublishSubject

object Router {

    private val history = mutableListOf<Route>()
    private val subject = PublishSubject.create<Route>()

    var historySize: Int = 0
        get() = history.size
        private set

    fun getInstance():PublishSubject<Route> {
        return subject
    }

    fun goto(navItem: Route) {
        history.add(navItem)
        subject.onNext(navItem)
    }

    interface Route {
        val id: String
        val args: Bundle
    }

    object Recipe: Route {
        override val id = "ROUTE_RECIPE"
        override val args = Bundle()
        fun route(id: String): Route {
            args.putString("id", id)
            return this
        }
    }

    object RecipeList: Route {
        override val id = "ROUTE_RECIPE_LIST"
        override val args = Bundle()
        fun route(): Route {
            return this
        }
    }
}
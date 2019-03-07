package com.twobrothers.overcooked.app

import android.os.Bundle
import io.reactivex.subjects.PublishSubject

object Navigation {

    private val history = mutableListOf<NavItem>()
    private val subject = PublishSubject.create<NavItem>()

    fun getInstance():PublishSubject<NavItem> {
        return subject
    }

    fun push(navItem: NavItem) {
        history.add(navItem)
        subject.onNext(navItem)
    }

    class NavItem(val tag: String = "", val args: Bundle? = null)
}
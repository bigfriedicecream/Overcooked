package com.twobrothers.overcooked.app

import io.reactivex.subjects.PublishSubject

object Navigation {

    private val history = mutableListOf<String>()
    private val subject = PublishSubject.create<String>()

    fun getInstance():PublishSubject<String> {
        return subject
    }

    fun push(tag: String) {
        history.add(tag)
        subject.onNext(tag)
    }
}
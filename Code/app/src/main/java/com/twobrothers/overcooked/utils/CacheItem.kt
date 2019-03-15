package com.twobrothers.overcooked.utils

class CacheItem<T>(val data:T, val cacheLength:Long) {

    private val timestamp:Long = System.currentTimeMillis()

    fun isFresh(): Boolean {
        val timeElapsed = System.currentTimeMillis() - timestamp
        return timeElapsed < cacheLength / 2
    }

    fun isExpiring(): Boolean {
        val timeElapsed = System.currentTimeMillis() - timestamp
        return timeElapsed >= cacheLength / 2 && timeElapsed < cacheLength
    }

    fun isExpired(): Boolean {
        val timeElapsed = System.currentTimeMillis() - timestamp
        return timeElapsed >= cacheLength
    }


}
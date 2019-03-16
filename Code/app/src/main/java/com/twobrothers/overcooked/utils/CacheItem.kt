package com.twobrothers.overcooked.utils

class CacheItem(val data: Any, val cacheLength: Int) {

    private val timestamp: Long = System.currentTimeMillis()

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
package com.twobrothers.overcooked.utils

class CacheItem<T>(val data:T, val expiry:Long) {
    private val timestamp:Long = System.currentTimeMillis()
    fun isExpiring(): Boolean {
        val cacheLength = expiry - timestamp
        return expiry - System.currentTimeMillis() > 0 && expiry - System.currentTimeMillis() < cacheLength / 2
    }
    fun isExpired(): Boolean {
        return System.currentTimeMillis() > expiry
    }
}
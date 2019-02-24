package com.twobrothers.overcooked.utils

class CacheItem<T>(val data:T, val expiry:Long = 1000 * 60 * 2)
package com.bigfriedicecream.overcooked.utils

fun Int.Companion.minsToPrettyTimeFormat(mins:Int):String {
    val hours:Int = mins / 60
    val leftoverMins:Int = mins % 60
    return if (hours > 0) "${hours}H ${leftoverMins}MIN" else "${mins}MIN"
}
package com.bigfriedicecream.overcooked.utils

import kotlin.math.roundToInt

fun Int.Companion.minsToPrettyTimeFormat(mins:Int):String {
    val hours:Int = mins / 60
    val leftoverMins:Int = mins % 60
    return if (hours > 0) "${hours}H ${leftoverMins}MIN" else "${mins}MIN"
}

fun Double.Companion.fractionFromNumber(value:Double):String {
    val intPart = value.toInt()
    val fractionPart = value - intPart

    if (fractionPart > 12.5 && fractionPart < 29.0) return (if (intPart > 0) "$intPart " else "") + "1/4"
    if (fractionPart > 29.0 && fractionPart < 41.5) return (if (intPart > 0) "$intPart " else "") + "1/3"
    if (fractionPart > 41.5 && fractionPart < 58.0) return (if (intPart > 0) "$intPart " else "") + "1/2"
    if (fractionPart > 58.0 && fractionPart < 70.5) return (if (intPart > 0) "$intPart " else "") + "2/3"
    if (fractionPart > 70.5 && fractionPart < 87.5) return (if (intPart > 0) "$intPart " else "") + "3/4"

    return value.roundToInt().toString()
}
package com.twobrothers.overcooked.utils

import android.os.Build
import android.text.Html
import android.text.Spanned
import kotlin.math.roundToInt

fun Double.Companion.toFraction(value:Double):String {
    val intPart = value.toInt()
    val fractionPart = value - intPart

    if (fractionPart > 0.125 && fractionPart < 0.29) return (if (intPart > 0) "$intPart" else "") + String.fromHtml("&#65279;<sup>1</sup>&frasl;<sub>4</sub>")
    if (fractionPart > 0.29 && fractionPart < 0.415) return (if (intPart > 0) "$intPart" else "") + String.fromHtml("&#65279;<sup>1</sup>&frasl;<sub>3</sub>")
    if (fractionPart > 0.415 && fractionPart < 0.58) return (if (intPart > 0) "$intPart" else "") + String.fromHtml("&#65279;<sup>1</sup>&frasl;<sub>2</sub>")
    if (fractionPart > 0.58 && fractionPart < 0.705) return (if (intPart > 0) "$intPart" else "") + String.fromHtml("&#65279;<sup>2</sup>&frasl;<sub>3</sub>")
    if (fractionPart > 0.705 && fractionPart < 0.875) return (if (intPart > 0) "$intPart" else "") + String.fromHtml("&#65279;<sup>3</sup>&frasl;<sub>4</sub>")

    return value.roundToInt().toString()
}

fun String.Companion.fromHtml(html:String): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY) else Html.fromHtml(html)
}
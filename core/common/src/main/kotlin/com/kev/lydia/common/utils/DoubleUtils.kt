package com.kev.lydia.common.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Double.toFormatPrice(): String {
    val formatter = NumberFormat.getNumberInstance(Locale.FRANCE) as DecimalFormat
    formatter.applyPattern("#,###")
    return formatter.format(this)
}
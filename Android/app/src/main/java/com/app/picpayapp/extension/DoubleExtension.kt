package com.app.picpayapp.extension

import java.text.NumberFormat
import java.util.*

fun Double?.currencyFormatter(): String = NumberFormat.getCurrencyInstance(
    Locale("pt", "BR")).format(this) ?: "R$ 0,00"
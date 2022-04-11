package com.app.picpayapp.data.transaction

import com.app.picpayapp.data.CreditCard
import com.app.picpayapp.data.User
import java.util.*

data class Transaction(
    val code: String = "",
    val origin: User = User(),
    val destination: User = User(),
    val dateTime: String = "",
    val isCreditCard: Boolean = false,
    val value: Double = 0.0,
    val creditCard: CreditCard = CreditCard()
) {
    companion object {
        fun hashGenerator(): String = UUID.randomUUID().toString()
    }
}

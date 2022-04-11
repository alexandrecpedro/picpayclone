package com.app.picpayapp.data.transaction

import com.app.picpayapp.data.CreditCard
import com.app.picpayapp.data.User

data class TransactionNetwork(
    val code: String?,
    val origin: User?,
    val destination: User?,
    val dateTime: String?,
    val isCreditCard: Boolean?,
    val value: Double?,
    val creditCard: CreditCard?
)
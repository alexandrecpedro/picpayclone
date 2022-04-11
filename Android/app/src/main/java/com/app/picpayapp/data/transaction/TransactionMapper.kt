package com.app.picpayapp.data.transaction

import com.app.picpayapp.data.CreditCard
import com.app.picpayapp.data.User

fun TransactionNetwork.toModel(): Transaction {
    return Transaction(
        code = code.orEmpty(),
        origin = origin ?: User(),
        destination = destination ?: User(),
        dateTime = dateTime.orEmpty(),
        isCreditCard = isCreditCard ?: false,
        value = value ?: 0.0,
        creditCard = creditCard ?: CreditCard()
    )
}

fun List<TransactionNetwork>.toModel() = this.map { it.toModel() }


fun Transaction.toLocal(): LocalTransaction = LocalTransaction(
    code = code,
    origin = origin.login,
    destination = destination.login,
    dateTime = dateTime,
    isCreditCard = isCreditCard,
    value = value
)

fun List<Transaction>.toLocal() = this.map { it.toLocal() }
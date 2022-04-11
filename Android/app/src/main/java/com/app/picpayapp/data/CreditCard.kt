package com.app.picpayapp.data

data class CreditCard(
    val brand: CardBrand = CardBrand.VISA,
    val cardNumber: String = "",
    val cardholderName: String = "",
    val expirationDate: String = "",
    val securityCode: String = "123",
    val tokenNumber: String = "",
    val user: User = User(),
    val isSaved: Boolean = false
)
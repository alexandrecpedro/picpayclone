package com.app.picpayapp.data

object LoggedUser {

    lateinit var token: Token

    lateinit var user: User

    fun setBalance(balance: Double) {
        user.balance = balance
    }

    fun isLogged(): Boolean = this::token.isInitialized

    fun isNotLogged(): Boolean = !isLogged()

}
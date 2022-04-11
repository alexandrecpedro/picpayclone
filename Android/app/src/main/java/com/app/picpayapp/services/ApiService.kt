package com.app.picpayapp.services

import com.app.picpayapp.data.Login
import com.app.picpayapp.data.Token
import com.app.picpayapp.data.User
import com.app.picpayapp.data.transaction.Transaction
import com.app.picpayapp.data.transaction.TransactionPage
import retrofit2.http.*

interface ApiService {

    @POST("/authentication")
    suspend fun authenticate(@Body login: Login): Token

    @GET("/users/contacts/")
    suspend fun getAllUsers(@Query("login") login: String): List<User>

    @GET("/users/{login}")
    suspend fun getUser(@Path("login") login: String): User

    @GET("/users/{login}/balance")
    suspend fun getBalance(@Path("login") login: String): User

    @POST("/transactions")
    suspend fun makeTransaction(@Body transaction: Transaction): Transaction

    @GET("/transactions")
    suspend fun getTransactions(@Query("login") login: String): TransactionPage

}
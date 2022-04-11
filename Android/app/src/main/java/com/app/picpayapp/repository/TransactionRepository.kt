package com.app.picpayapp.repository

import com.app.picpayapp.data.User
import com.app.picpayapp.data.transaction.Transaction
import com.app.picpayapp.data.transaction.TransactionDAO
import com.app.picpayapp.data.transaction.toLocal
import com.app.picpayapp.data.transaction.toModel
import com.app.picpayapp.services.ApiService

interface TransactionRepository {

    suspend fun getBalance(login: String): User

    suspend fun getTransactions(login: String): List<Transaction>
}

class TransactionRepositoryImpl(
    private val apiService: ApiService,
    private val transactionDAO: TransactionDAO
) : TransactionRepository {

    override suspend fun getBalance(login: String): User = apiService.getBalance(login)

    override suspend fun getTransactions(login: String): List<Transaction> {
        val transactions = apiService.getTransactions(login).content.toModel()
        transactionDAO.save(transactions.toLocal())
        return transactions
    }

}
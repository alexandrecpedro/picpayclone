package com.app.picpayapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.picpayapp.data.State
import com.app.picpayapp.data.transaction.Transaction
import com.app.picpayapp.data.LoggedUser
import com.app.picpayapp.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TransactionRepository) : ViewModel() {

    val balanceState = MutableLiveData<State<Double>>()
    val transactionState = MutableLiveData<State<List<Transaction>>>()

    init {
        val login = LoggedUser.user.login
        getBalance(login)
        getHistory(login)
    }

    private fun getHistory(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionState.postValue(State.Loading())
            try {
                val history = repository.getTransactions(login)
                transactionState.postValue(State.Success(history))
            } catch (e: Exception) {
                transactionState.postValue(State.Error(e))
            }
        }
    }

    private fun getBalance(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            balanceState.postValue(State.Loading())
            try {
                val newBalance = repository.getBalance(login).balance
                LoggedUser.setBalance(newBalance)
                balanceState.postValue(State.Success(newBalance))
            } catch (e: Exception) {
                balanceState.postValue(State.Error(e))
            }
        }
    }
}
package com.app.picpayapp.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.picpayapp.data.transaction.Transaction
import com.app.picpayapp.services.ApiService
import kotlinx.coroutines.launch

class TransactionViewModel(private val apiService: ApiService) : ViewModel() {

    private val _transfer = MutableLiveData<Transaction>()
    val transfer: LiveData<Transaction> = _transfer
    val onError = MutableLiveData<String>()

    fun transferValue(transaction: Transaction) {
        viewModelScope.launch {
            try {
                val transferredValue = apiService.makeTransaction(transaction)
                _transfer.value = transferredValue
            } catch (e: Exception) {
                onError.value = "Impossible to transfer"
            }
        }
    }
}
package com.app.picpayapp.ui.pay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.picpayapp.data.LoggedUser
import com.app.picpayapp.data.User
import com.app.picpayapp.services.ApiService
import kotlinx.coroutines.launch

class PayViewModel(private val apiService: ApiService) : ViewModel() {

    private val _contacts = MutableLiveData<List<User>>()
    val contacts: LiveData<List<User>> = _contacts
    val onLoading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<String>()

    init {
        // We can init a suspend fun directly
        // Firstly, we should call a Scope for ViewModel
        viewModelScope.launch {
            onLoading.value = true
            try {
                val users = apiService.getAllUsers(LoggedUser.user.login)
                _contacts.value = users
            } catch (e: Exception) {
                onError.value = e.message
            }
            onLoading.value = false
        }
    }
}
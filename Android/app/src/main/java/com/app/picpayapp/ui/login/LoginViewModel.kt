package com.app.picpayapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.picpayapp.data.Login
import com.app.picpayapp.data.State
import com.app.picpayapp.data.User
import com.app.picpayapp.data.LoggedUser
import com.app.picpayapp.services.ApiService
import kotlinx.coroutines.launch

class LoginViewModel(private val apiService: ApiService) : ViewModel() {

    val userState = MutableLiveData<State<User>>()

    fun login(login: Login) {
        userState.value = State.Loading()
        viewModelScope.launch {
            try {
                val token = apiService.authenticate(login)
                LoggedUser.token = token
                val user = apiService.getUser(login.user)
                LoggedUser.user = user
                userState.value = State.Success(user)
            } catch (e: Exception) {
                userState.value = State.Error(e)
            }
        }
    }
}
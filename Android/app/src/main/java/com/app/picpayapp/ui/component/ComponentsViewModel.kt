package com.app.picpayapp.ui.component

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComponentsViewModel : ViewModel() {

    private val _components = MutableLiveData<Components>().also {
        it.value = hasComponents
    }
    val components: LiveData<Components> = _components

    var hasComponents = Components()
        set(value) {
            field = value
            _components.value = value
        }
}

data class Components(
    val bottomNavigation: Boolean = false
)
package com.app.picpayapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.picpayapp.ui.component.Components
import com.app.picpayapp.ui.component.ComponentsViewModel
import com.app.picpayapp.R
import com.app.picpayapp.data.LoggedUser
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment() {

    private val componentsViewModel: ComponentsViewModel by sharedViewModel()
    private val settingsViewModel: SettingsViewModel by viewModel()
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentsViewModel.hasComponents = Components(bottomNavigation = true)
        setExitButton()
        setUserData()
    }

    private fun setUserData() {
        LoggedUser.user.let { user ->
            textViewLoginMain.text = user.login
            textViewFullName.text = user.fullName
            textViewLogin.text = user.login
            textViewEmail.text = user.email
            textViewNumber.text = user.telephoneNumber
        }
    }

    private fun setExitButton() {
        buttonExit.setOnClickListener {}
    }

}
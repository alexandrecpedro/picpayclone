package com.app.picpayapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.picpayapp.ui.component.Components
import com.app.picpayapp.ui.component.ComponentsViewModel
import com.app.picpayapp.R
import com.app.picpayapp.data.Login
import com.app.picpayapp.data.State
import com.app.picpayapp.extension.hide
import com.app.picpayapp.extension.getString
import com.app.picpayapp.extension.show
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.login_fragment) {

    private val componentsViewModel: ComponentsViewModel by sharedViewModel()
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentsViewModel.hasComponents = Components(bottomNavigation = false)
        setLoginButton()
        observeUserStates()
    }

    private fun observeUserStates() {
        viewModel.userState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    progressBar.show()
                }
                is State.Success -> {
                    progressBar.hide()
                    val direction = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
                    findNavController().navigate(direction)
                }
                is State.Error -> {
                    progressBar.hide()
                    Toast.makeText(
                        requireContext(),
                        "Authentication failure",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setLoginButton() {
        buttonLogin.setOnClickListener {
            val user = textInputUserLayout.getString()
            val password = textInputPasswordLayout.getString()
            val login = Login(user, password)
            viewModel.login(login)
        }
    }
}
package com.app.picpayapp.ui.pay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.picpayapp.ui.component.Components
import com.app.picpayapp.ui.component.ComponentsViewModel
import com.app.picpayapp.R
import com.app.picpayapp.data.User
import com.app.picpayapp.extension.show
import com.app.picpayapp.extension.vanish
import kotlinx.android.synthetic.main.pay_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PayFragment : Fragment() {

    private val componentsViewModel: ComponentsViewModel by sharedViewModel()
    private val payViewModel: PayViewModel by viewModel()
    // Lazy = Android normally creates Fragment with all attributes
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pay_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentsViewModel.hasComponents = Components(bottomNavigation = true)
        observeContacts()
        observeLoading()
    }

    private fun observeLoading() {
        payViewModel.onLoading.observe(viewLifecycleOwner, Observer { onLoading ->
            if(onLoading) {
                progressBarPay.show()
                recyclerView.vanish()
            } else {
                progressBarPay.vanish()
                recyclerView.show()
            }
        })
    }

    private fun observeContacts() {
        payViewModel.contacts.observe(viewLifecycleOwner, Observer {
            setRecyclerView(it)
        })
    }

    private fun setRecyclerView(users: List<User>) {
        recyclerView.adapter = PayAdapter(users) {
            val direction = PayFragmentDirections.actionNavigationPayToNavigationTransfer(it)
            controller.navigate(direction)
        }
    }
}
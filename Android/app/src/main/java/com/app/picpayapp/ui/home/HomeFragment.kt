package com.app.picpayapp.ui.home

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
import com.app.picpayapp.data.State
import com.app.picpayapp.data.transaction.Transaction
import com.app.picpayapp.data.LoggedUser
import com.app.picpayapp.extension.vanish
import com.app.picpayapp.extension.hide
import com.app.picpayapp.extension.currencyFormatter
import com.app.picpayapp.extension.show
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val componentsViewModel: ComponentsViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentsViewModel.hasComponents = Components(bottomNavigation = true)
        if(LoggedUser.isNotLogged()) {
            val direction = HomeFragmentDirections.actionGlobalLoginFragment()
            controller.navigate(direction)
            return
        }
        observeBalanceState()
        observeTransactionsState()
    }

    private fun observeTransactionsState() {
        homeViewModel.transactionState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    showProgressTransaction()
                }
                is State.Success -> {
                    hideProgressTransaction()
                    setRecyclerView(it.data)
                }
                is State.Error -> {
                    hideProgressTransaction()
                    setRecyclerView(mutableListOf())
                    Toast.makeText(this.context, it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun observeBalanceState() {
        homeViewModel.balanceState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    showProgressBalance()
                }
                is State.Success -> {
                    hideProgressBalance()
                    textViewBalance.text = it.data.currencyFormatter()
                }
                is State.Error -> {
                    hideProgressBalance()
                    Toast.makeText(this.context, it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun hideProgressBalance() {
        progressBarBalance.vanish()
        textViewBalance.show()
        textViewLabelBalance.show()
    }

    private fun showProgressBalance() {
        progressBarBalance.show()
        textViewBalance.hide()
        textViewLabelBalance.hide()
    }

    private fun showProgressTransaction() {
        progressBarTransferences.show()
        recyclerView.vanish()
    }

    private fun hideProgressTransaction() {
        progressBarTransferences.vanish()
        recyclerView.show()
    }

    private fun setRecyclerView(transfers: List<Transaction>) {
        recyclerView.adapter = HomeAdapter(transfers)
    }
}
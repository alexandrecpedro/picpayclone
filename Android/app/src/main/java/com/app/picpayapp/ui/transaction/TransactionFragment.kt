package com.app.picpayapp.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.picpayapp.ui.component.Components
import com.app.picpayapp.ui.component.ComponentsViewModel
import com.app.picpayapp.R
import com.app.picpayapp.data.CardBrand
import com.app.picpayapp.data.CreditCard
import com.app.picpayapp.data.LoggedUser
import com.app.picpayapp.data.User
import com.app.picpayapp.data.transaction.Transaction
import com.app.picpayapp.extension.format
import com.app.picpayapp.extension.show
import com.app.picpayapp.extension.vanish
import kotlinx.android.synthetic.main.transfer_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TransactionFragment : Fragment() {

    private val componentsViewModel: ComponentsViewModel by sharedViewModel()
    private val transactionViewModel: TransactionViewModel by viewModel()
    private val arguments by navArgs<TransactionFragmentArgs>()
    private val user by lazy { arguments.user }
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transfer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentsViewModel.hasComponents = Components(bottomNavigation = false)
        setUserData()
        setRadioGroup()
        setTransferButton()
        observeTransfer()
        observeError()
    }

    private fun observeError() {
        transactionViewModel.onError.observe(viewLifecycleOwner, Observer {
            it?.let { message ->
                Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observeTransfer() {
        transactionViewModel.transfer.observe(viewLifecycleOwner, Observer {
            val direction =
                TransactionFragmentDirections.actionNavigationTransferenceToNavigationPay()
            controller.navigate(direction)
        })
    }

    private fun setTransferButton() {
        buttonTransfer.setOnClickListener {
            val transference = newTransference()
            transactionViewModel.transferValue(transference)
        }
    }

    private fun newTransference(): Transaction {
        val userOrigin = LoggedUser.user
        val isCreditCard = radioButtonCreditCard.isChecked
        val dateTime = Calendar.getInstance().format()
        val value = getValue()
        return if (isCreditCard) {
            newTransferenceWithCreditCard(userOrigin, dateTime, isCreditCard, value)
        } else {
            newTransferenceWithoutCreditCard(userOrigin, dateTime, isCreditCard, value)
        }
    }

    private fun newTransferenceWithoutCreditCard(
        userOrigin: User,
        dateTime: String,
        isCreditCard: Boolean,
        value: Double
    ): Transaction {
        return Transaction(
            Transaction.hashGenerator(),
            userOrigin,
            user,
            dateTime,
            isCreditCard,
            value
        )
    }

    private fun newTransferenceWithCreditCard(
        userOrigin: User,
        dateTime: String,
        isCreditCard: Boolean,
        value: Double
    ): Transaction {
        return Transaction(
            Transaction.hashGenerator(),
            userOrigin,
            user,
            dateTime,
            isCreditCard,
            value,
            newCreditCard(userOrigin)
        )
    }

    private fun getValue(): Double {
        val value = editTextValue.text.toString()
        return if (value.isEmpty()) {
            0.0
        } else {
            value.toDouble()
        }
    }

    private fun newCreditCard(userOrigin: User): CreditCard {
        val cardNumber = editTextCardNumber.text.toString()
        val cardholderName = editTextCardholderName.text.toString()
        val expirationDate = editTextExpiration.text.toString()
        val cvv = editTextCVV.text.toString()
        return CreditCard(
            CardBrand.VISA,
            cardNumber,
            cardholderName,
            expirationDate,
            cvv,
            "",
            userOrigin
        )
    }

    private fun setUserData() {
        textViewName.text = user.login
        textViewFullName.text = user.fullName
    }

    private fun setRadioGroup() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            // when = switch/case for Kotlin
            when (checkedId) {
                R.id.radioButtonCreditCard -> {
                    constraintLayoutCreditCard.show()
                }
                else -> {
                    constraintLayoutCreditCard.vanish()
                }
            }
        }
    }


}
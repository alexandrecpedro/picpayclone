package com.app.picpayapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.picpayapp.R
import com.app.picpayapp.data.transaction.Transaction
import com.app.picpayapp.extension.currencyFormatter
import kotlinx.android.synthetic.main.transaction_item.view.*

class HomeAdapter(private val transactions: List<Transaction> = listOf()) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = transactions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(transaction: Transaction) {
            with(itemView) {
                textViewOrigin.text = transaction.origin.fullName
                textViewDestination.text = transaction.destination.fullName
                textViewValue.text = transaction.value.currencyFormatter()
                textViewData.text = transaction.dateTime
                textViewCircle.text =
                    transaction.origin.fullName.first().uppercaseChar().toString()
            }
        }
    }

}
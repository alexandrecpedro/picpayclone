package com.app.picpayapp.ui.pay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.picpayapp.R
import com.app.picpayapp.data.User
import kotlinx.android.synthetic.main.contact_item.view.*

class PayAdapter(private val users: List<User>, val onClick: (User) -> Unit) :
    RecyclerView.Adapter<PayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            with(itemView) {
                textViewName.text = user.login
                textViewFullName.text = user.fullName
                setOnClickListener { 
                    onClick(user)
                }
            }
        }
    }
}

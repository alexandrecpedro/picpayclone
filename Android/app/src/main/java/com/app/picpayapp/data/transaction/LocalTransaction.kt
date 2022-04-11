package com.app.picpayapp.data.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class LocalTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val code: String = "",
    val origin: String = "",
    val destination: String = "",
    val dateTime: String = "",
    val isCreditCard: Boolean = false,
    val value: Double = 0.0
)
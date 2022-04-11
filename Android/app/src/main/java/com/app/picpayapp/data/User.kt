package com.app.picpayapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val login: String = "",
    val password: String = "",
    val email: String = "",
    val fullName: String = "",
    val cpf: String = "",
    val birthday: String = "",
    val telephoneNumber: String = "",
    var balance: Double = 0.0
) : Parcelable

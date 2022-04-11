package com.app.picpayapp.extension

import android.view.View
import com.google.android.material.textfield.TextInputLayout

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.vanish() {
    this.visibility = View.INVISIBLE
}

fun TextInputLayout.getString() = this.editText?.text.toString()
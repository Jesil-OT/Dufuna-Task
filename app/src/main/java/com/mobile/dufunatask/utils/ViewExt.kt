package com.mobile.dufunatask.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout


fun View.displaySnack(context: Context, message: String?) {
    if (message != null) {
        Snackbar.make(context, this, message, Snackbar.LENGTH_LONG).show()
    }
}

fun Context.displayToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.hideKeyboard(view: View) {
    try {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (ignored: Exception) {
        ignored.printStackTrace()
    }
}


fun View.hide() {
    this.isVisible = false
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.show(){
    this.isVisible = true
}


package com.example.punkproject

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

object Extension {
    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showAlert(title: String, message: String) {
        AlertDialog.Builder(this).setTitle(title).setMessage(message).create().show()
    }

    fun Context.showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}
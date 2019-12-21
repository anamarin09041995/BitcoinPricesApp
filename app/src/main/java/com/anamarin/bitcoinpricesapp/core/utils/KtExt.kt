package com.anamarin.bitcoinpricesapp.core.utils

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

fun Any.snack(activity: AppCompatActivity, duration: Int = Snackbar.LENGTH_INDEFINITE) {
    Snackbar.make(
        activity.window.decorView.findViewById(android.R.id.content),
        this.toString(),
        duration
    ).apply { setAction("OK") { this.dismiss() } }.show()
}
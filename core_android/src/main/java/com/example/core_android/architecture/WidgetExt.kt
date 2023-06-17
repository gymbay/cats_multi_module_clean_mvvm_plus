package com.example.core_android.architecture

import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView

fun TextView.update(newText: CharSequence?) {
    if (this is EditText) {
        if (text.toString() != newText?.toString().orEmpty()) {
            setText(newText)
        }
    } else {
        text = newText
    }
}

fun CompoundButton.update(checked: Boolean) {
    if (this.isChecked != checked) {
        this.isChecked = checked
    }
}
package com.example.feature_dialogs.alert.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlertData(
    val title: String? = null,
    val message: String? = null,
    val btnCancelTitle: String? = null,
    val btnConfirmTitle: String? = null,
) : Parcelable

package com.example.feature_dialogs.alert.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class AlertData(
    val title: String?,
    val message: String?,
    val btnCancelTitle: String?,
    val btnConfirmTitle: String?,
) : Parcelable

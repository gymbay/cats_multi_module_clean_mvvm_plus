package com.example.feature_dialogs.alert.ui.extensions

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.feature_dialogs.alert.ui.AlertDialogFragment
import com.example.feature_dialogs.alert.ui.models.AlertConstants.ALERT_DATA_MODEL_KEY
import com.example.feature_dialogs.alert.ui.models.AlertConstants.ALERT_DIALOG_TAG
import com.example.feature_dialogs.alert.ui.models.AlertData

fun Fragment.showAlert(alertData: AlertData) {
    if (!isAdded) return
    val manager = parentFragmentManager
    if (manager.findFragmentByTag(ALERT_DIALOG_TAG) != null) return

    val alert = AlertDialogFragment()
    alert.arguments = bundleOf(
        ALERT_DATA_MODEL_KEY to alertData
    )
    alert.show(manager, ALERT_DIALOG_TAG)
}
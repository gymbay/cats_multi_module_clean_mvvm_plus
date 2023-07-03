package com.example.feature_dialogs.alert.ui.extensions

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.clearFragmentResult
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import com.example.feature_dialogs.alert.ui.AlertDialogFragment
import com.example.feature_dialogs.alert.ui.models.AlertConstants.ALERT_DATA_MODEL_KEY
import com.example.feature_dialogs.alert.ui.models.AlertConstants.ALERT_DIALOG_BUTTON_CANCEL_RESULT
import com.example.feature_dialogs.alert.ui.models.AlertConstants.ALERT_DIALOG_BUTTON_CONFIRM_RESULT
import com.example.feature_dialogs.alert.ui.models.AlertConstants.ALERT_DIALOG_TAG
import com.example.feature_dialogs.alert.ui.models.AlertData

fun Fragment.showAlert(
    title: String = "",
    message: String = "",
    btnCancelTitle: String? = null,
    btnConfirmTitle: String? = null,
    onCancelClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {}
) {
    if (!isAdded) return
    val manager = parentFragmentManager
    if (manager.findFragmentByTag(ALERT_DIALOG_TAG) != null) return

    val alert = AlertDialogFragment()
    alert.arguments = bundleOf(
        ALERT_DATA_MODEL_KEY to AlertData(
            title,
            message,
            btnCancelTitle,
            btnConfirmTitle
        )
    )

    clearFragmentResult(ALERT_DIALOG_BUTTON_CANCEL_RESULT)
    clearFragmentResultListener(ALERT_DIALOG_BUTTON_CANCEL_RESULT)
    setFragmentResultListener(ALERT_DIALOG_BUTTON_CANCEL_RESULT) { _, _ ->
        onCancelClick()
    }

    clearFragmentResult(ALERT_DIALOG_BUTTON_CONFIRM_RESULT)
    clearFragmentResultListener(ALERT_DIALOG_BUTTON_CONFIRM_RESULT)
    setFragmentResultListener(ALERT_DIALOG_BUTTON_CONFIRM_RESULT) { _, _ ->
        onConfirmClick()
    }

    alert.show(manager, ALERT_DIALOG_TAG)
}
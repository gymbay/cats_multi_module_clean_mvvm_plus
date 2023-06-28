package com.example.feature_dialogs.alert.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.feature_dialogs.R
import com.example.feature_dialogs.alert.ui.models.AlertConstants.ALERT_DATA_MODEL_KEY
import com.example.feature_dialogs.alert.ui.models.AlertConstants.ALERT_DIALOG_BUTTON_CANCEL_RESULT
import com.example.feature_dialogs.alert.ui.models.AlertConstants.ALERT_DIALOG_BUTTON_CONFIRM_RESULT
import com.example.feature_dialogs.databinding.AlertDialogBinding
import com.example.feature_dialogs.alert.ui.models.AlertData

internal class AlertDialogFragment : DialogFragment() {

    private val binding: AlertDialogBinding
        get() = _binding!!
    private var _binding: AlertDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = AlertDialogBinding.inflate(inflater)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alertData: AlertData = checkNotNull(arguments?.getParcelable(ALERT_DATA_MODEL_KEY))
        initAlertViews(alertData)
        initListeners()
    }

    private fun initAlertViews(alertData: AlertData) {
        with(binding) {
            tvTitle.text = alertData.title
            tvMessage.text = alertData.message
            if (alertData.btnCancelTitle != null) {
                btnCancel.text = alertData.btnCancelTitle
            } else {
                btnCancel.isVisible = false
            }
            btnConfirm.text = alertData.btnConfirmTitle
                ?: getText(R.string.feature_dialogs_btn_right_standard_title)
        }
    }

    private fun initListeners() {
        with(binding) {
            btnCancel.setOnClickListener {
                onButtonClick(ALERT_DIALOG_BUTTON_CANCEL_RESULT)
            }
            btnConfirm.setOnClickListener {
                onButtonClick(ALERT_DIALOG_BUTTON_CONFIRM_RESULT)
            }
        }
    }

    private fun onButtonClick(key: String) {
        setFragmentResult(key, bundleOf())
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
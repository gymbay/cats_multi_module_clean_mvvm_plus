package com.example.feature_dialogs.ui.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.feature_dialogs.databinding.AlertDialogBinding
import com.example.feature_dialogs.ui.models.AlertData

internal class AlertDialog : DialogFragment() {

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

        //val alertData: AlertData = arguments.getParcelable(ALERT_DATA_MODEL_KEY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ALERT_DATA_MODEL_KEY = "ALERT_DATA_MODEL_KEY"

    }

}
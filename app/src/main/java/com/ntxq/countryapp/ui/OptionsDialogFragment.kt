package com.ntxq.countryapp.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.ntxq.countryapp.databinding.FragmentOptionsDialogBinding
import com.ntxq.countryapp.model.SavedCountry

class OptionsDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(onClickYesButton: () -> Unit): OptionsDialogFragment {
            val dialog = OptionsDialogFragment()
            dialog.onClickYesButton = onClickYesButton
            return dialog
        }
    }
    private var _binding: FragmentOptionsDialogBinding? = null
    private val binding get() = _binding!!
    private var onClickYesButton: (() -> Unit)? = null
    override fun onResume() {
        super.onResume()
        val params = dialog?.window?.attributes ?: return
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOptionsDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNo.setOnClickListener { dialog?.dismiss() }
        binding.buttonYes.setOnClickListener {
            onClickYesButton?.invoke()
            dialog?.dismiss()
        }
    }
}
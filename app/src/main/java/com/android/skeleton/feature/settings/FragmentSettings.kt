package com.android.skeleton.feature.settings

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import com.android.skeleton.databinding.FragmentSettingsBinding

/**
 * Displays settings that tweak app behaviour.
 */
@AndroidEntryPoint
class FragmentSettings: BottomSheetDialogFragment() {
    private val viewModel: ViewModelSettings by viewModels()
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater)
        binding.viewModel = viewModel
        setActionListeners()
        fillInputFields()

        val inputService =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputService?.hideSoftInputFromWindow(binding.root.windowToken, 0)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Populates input fields.
     */
    private fun fillInputFields() {
        binding.frSettingsSettingytext.setText(viewModel.settingY())
    }

    /**
     * Sets responses to ui interactions.
     */
    private fun setActionListeners() {
        binding.frSettingsSettingytext.doAfterTextChanged { text ->
            viewModel.changeSettingY(text)
        }
    }
}
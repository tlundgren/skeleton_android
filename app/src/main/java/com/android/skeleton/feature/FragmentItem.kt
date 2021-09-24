package com.android.skeleton.feature

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.android.skeleton.R
import com.android.skeleton.databinding.FragmentItemBinding
import com.android.skeleton.domain.Item

/**
 * Allows users to create or modify an [Item].
 */
class FragmentItem : Fragment() {
    private val fragmentArguments: FragmentItemArgs by navArgs()
    private val viewModel: ViewModelItem by viewModels {
        ViewModelItemFactory(
            requireActivity().application, fragmentArguments.isNew, fragmentArguments.item
        )
    }

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater)
        fillInputFields()
        setObservers()
        setActionListeners()
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Initializes fields with view model data.
     */
    private fun fillInputFields() {
        binding.apply {
            frItemNametext.setText(viewModel.item.name)
            frItemNametext.isEnabled = viewModel.isNameEditable
            frItemDescriptiontext.setText(viewModel.item.description)
        }
    }

    /**
     * Sets responses to view model updates.
     */
    private fun setObservers() {
        viewModel.signalDismiss.observe(viewLifecycleOwner) { dismiss ->
            signaledDismiss(dismiss)
        }
    }

    /**
     * Sets responses to UI interactions.
     */
    private fun setActionListeners() {
        binding.frItemOk.setOnClickListener {
            clickedButtonSave()
        }
        binding.frItemCancel.setOnClickListener {
            clickedButtonCancel()
        }
        binding.frItemToolbar.setNavigationOnClickListener {
            navigateBack()
        }
        binding.frItemToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.mnItem_ok -> clickedButtonSave()
                else -> clickedButtonCancel()
            }
            return@setOnMenuItemClickListener false
        }
    }

    /**
     * Asks view model to cancel the creation/edit.
     */
    private fun clickedButtonCancel() {
        viewModel.cancel()
    }

    /**
     * Asks view model to save the item, reports to user.
     */
    private fun clickedButtonSave() {
        viewModel.save(
            binding.frItemNametext.text,
            binding.frItemDescriptiontext.text
        )
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            R.string.frItem_savemessage,
            Snackbar.LENGTH_LONG
        ).show()
    }

    /**
     * If told to by [dismiss], navigates back.
     */
    private fun signaledDismiss(dismiss: Boolean) {
        if (dismiss) {
            viewModel.resetSignalDismiss()
            navigateBack()
        }
    }

    /**
     * Navigates back, hiding input if necessary.
     */
    private fun navigateBack() {
        // make sure keyboard disappears before navigating back
        val inputService =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputService?.hideSoftInputFromWindow(binding.root.windowToken, 0)
        findNavController().popBackStack()
    }

}
package com.android.skeleton.feature.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import com.android.skeleton.databinding.FragmentHeaderBinding
import com.android.skeleton.feature.item.ViewModelItems

/**
 * Displays a summary of the list of data.
 */
@AndroidEntryPoint
class FragmentHeader : Fragment() {
    private val viewModel: ViewModelItems by activityViewModels()
    private var _binding: FragmentHeaderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeaderBinding.inflate(inflater)
        binding.viewModel = viewModel
        setObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Sets responses to changes in [viewModel] state.
     */
    private fun setObservers() {
        viewModel.size.observe(viewLifecycleOwner) {
            updateCount(it)
        }
    }

    /**
     * Updates field count with [size].
     */
    private fun updateCount(size: Int) {
        binding.frHeaderCount.text = size.toString()
    }

}
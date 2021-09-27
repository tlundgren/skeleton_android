package com.android.skeleton.feature.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.android.skeleton.R
import com.android.skeleton.databinding.FragmentRecipesBinding
import com.google.android.material.snackbar.Snackbar

class FragmentRecipes: Fragment() {
    val viewModel: ViewModelRecipes by viewModels()
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.dataStatus.observe(viewLifecycleOwner) {
            dataLoadChanged(it)
        }
        setUpRecyclerView()
        setActionListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Sets up a recycler view, including data connection.
     */
    private fun setUpRecyclerView() {
        val manager = GridLayoutManager(
            context,
            1,
            GridLayoutManager.VERTICAL,
            false
        )
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
        binding.frRecipesList.layoutManager = manager
        val adapter = AdapterRecipe()
        binding.frRecipesList.adapter = adapter
        adapter.submitList(viewModel.recipes)
    }

    /**
     * Sets responses to UI interactions.
     */
    private fun setActionListeners() {
        binding.frRecipesLoadbutton.setOnClickListener() {
            clickedButtonLoad()
        }
    }

    /**
     * Ask view model to load the data.
     */
    private fun clickedButtonLoad() {
        viewModel.loadData()
    }

    /**
     * Updates the screen according to the new status of the data load operation.
     */
    private fun dataLoadChanged(status: DataLoadStatus) {
        when (status) {
            DataLoadStatus.IN_PROGRESS -> {
                binding.frRecipesProgress.visibility = View.VISIBLE
                binding.frRecipesLoadbutton.visibility = View.GONE
                binding.frRecipesLoadmessage.visibility = View.GONE
                binding.frRecipesList.visibility = View.VISIBLE
            }
            DataLoadStatus.FAILED -> {
                binding.frRecipesProgress.hide()
                binding.frRecipesLoadbutton.visibility = View.VISIBLE
                binding.frRecipesLoadmessage.visibility = View.VISIBLE
                binding.frRecipesList.visibility = View.GONE
            }
            DataLoadStatus.SUCCESSFUL -> {
                binding.frRecipesProgress.hide()
                binding.frRecipesLoadbutton.visibility = View.GONE
                binding.frRecipesLoadmessage.visibility = View.GONE
                binding.frRecipesList.visibility = View.VISIBLE
                val adapter = binding.frRecipesList.adapter as AdapterRecipe
                adapter.submitList(viewModel.recipes)
            }
        }
    }
}
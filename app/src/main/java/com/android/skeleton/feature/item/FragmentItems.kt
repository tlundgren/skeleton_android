package com.android.skeleton.feature.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import com.android.skeleton.databinding.FragmentItemsBinding
import com.android.skeleton.domain.Item
import com.android.skeleton.feature.FragmentPagerDirections

/**
 * Displays a list of [Item]s, the size of the list, a button to create new [Item]s.
 */
@AndroidEntryPoint
class FragmentItems : Fragment() {
    val viewModel: ViewModelItems by activityViewModels()
    private var _binding: FragmentItemsBinding? = null
    private val binding get() = _binding!!

    /**
     * allows item dragging functionality. when items must be swapped in the recycler view,
     * we make a sync swap in this list, then ask view model to replicate the changes in the
     * underlying data (async). item touch helper (dragging functionality) requires sync swaps.
     */
    private lateinit var uiList: MutableList<Item>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setObservers()
        setUpRecyclerView()

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
        viewModel.signalToItem.observe(viewLifecycleOwner) {itemData->
            signaledToItem(itemData)
        }
        viewModel.fabVisibility.observe(viewLifecycleOwner) {visibility->
            binding.frListFab.visibility = visibility
        }
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
        binding.frListList.layoutManager = manager
        val touchHelper =
            ItemTouchHelper(TouchCallbackItem { from, to ->
                // reorder the items in the ui list, sync swap
                val itemFrom = uiList[from]
                val itemTo = uiList[to]
                uiList[from] = itemTo
                uiList[to] = itemFrom
                binding.frListList.adapter?.notifyItemMoved(from, to)
                // replicate reordering in base list ie in position attribute, async swap
                viewModel.swapItems(itemFrom.position, itemTo.position)
            })
        val adapter = AdapterItem(
            ListenerItem(
                { item -> viewModel.editItem(item) },
                { item -> viewModel.deleteItem(item) }
            ),
            touchHelper
        )
        binding.frListList.adapter = adapter
        touchHelper.attachToRecyclerView(binding.frListList)
        viewModel.items.observe(viewLifecycleOwner) {
            uiList = it.toMutableList()
            adapter.submitList(uiList)
        }
    }

    /**
     * Navigates to [Item] screen.
     */
    private fun signaledToItem(itemData: ViewModelItems.ItemData?) {
        itemData?.let {
            viewModel.resetSignalToItem()
            findNavController()
                .navigate(
                    FragmentPagerDirections.actionFragmentPagerToFragmentItem(
                        itemData.isNew, itemData.item
                    )
                )
        }
    }
}
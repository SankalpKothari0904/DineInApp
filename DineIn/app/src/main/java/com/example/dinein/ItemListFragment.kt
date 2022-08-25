package com.example.dinein

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinein.databinding.FragmentItemListBinding
import com.example.dinein.viewModel.InventoryViewModelFactory
import com.example.dinein.viewModel.ItemViewModel


class ItemListFragment : Fragment() {
    private val viewModel: ItemViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as MenuItemApplication).database.itemDao()
        )
    }

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemListAdapter {
            val action =
                ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(it.id)
            this.findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter
        viewModel.allMenuItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes.

        binding.newItemButton.setOnClickListener {
            val action =ItemListFragmentDirections.actionItemListFragmentToAddItemFragment(getString(R.string.add_fragment_title),-1)
            this.findNavController().navigate(action)
        }

        binding.generateBillButton.setOnClickListener {
            val action = ItemListFragmentDirections.actionItemListFragmentToTableForBillFragment()
            this.findNavController().navigate(action)
        }
    }
}
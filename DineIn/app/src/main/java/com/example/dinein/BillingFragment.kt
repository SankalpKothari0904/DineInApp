package com.example.dinein

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinein.data.Item
import com.example.dinein.databinding.FragmentAddItemBinding
import com.example.dinein.databinding.FragmentBillingBinding
import com.example.dinein.databinding.FragmentItemListBinding
import com.example.dinein.viewModel.InventoryViewModelFactory
import com.example.dinein.viewModel.ItemViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.NumberFormat

class BillingFragment : Fragment() {
    private val viewModel: ItemViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as MenuItemApplication).database
                .itemDao()
        )
    }
    private val navigationArgs: BillingFragmentArgs by navArgs()

    private var _binding: FragmentBillingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("App flow", "AddItemCreated")
        _binding = FragmentBillingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BillingAdapter()
        binding.recyclerView.adapter = adapter
        lifecycle.coroutineScope.launch {
            viewModel.retrieveItemsOrderedAtaTable(navigationArgs.tableNum).collect() {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel.getBillingAmount(navigationArgs.tableNum).observe(this.viewLifecycleOwner){
            amt -> binding.billAmount.text = NumberFormat.getCurrencyInstance().format(amt)
        }

        binding.goBackButton.setOnClickListener {
            viewModel.deleteItemsAfterBilling(navigationArgs.tableNum)
            val action = BillingFragmentDirections.actionBillingFragmentToItemListFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
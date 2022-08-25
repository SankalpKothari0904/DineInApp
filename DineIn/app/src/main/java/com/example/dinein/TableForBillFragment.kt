package com.example.dinein

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.dinein.databinding.FragmentItemDetailBinding
import com.example.dinein.databinding.FragmentTableForBillBinding
import com.example.dinein.viewModel.InventoryViewModelFactory
import com.example.dinein.viewModel.ItemViewModel


class TableForBillFragment : Fragment() {
    private var _binding: FragmentTableForBillBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTableForBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener {
            val action = TableForBillFragmentDirections.actionTableForBillFragmentToBillingFragment(binding.tableNo.text.toString().toInt())
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}
package com.example.dinein

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dinein.data.Item
import com.example.dinein.data.getFormattedPrice
import com.example.dinein.databinding.ItemListItemBinding

class ItemListAdapter(private val onItemClicked: (Item)->Unit) : ListAdapter<Item, ItemListAdapter.ItemViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Item) {
                binding.itemName.text = item.itemName
                binding.itemPrice.text = item.getFormattedPrice()
                binding.itemQuantity.text = item.quantity.toString()
                binding.tableNo.text = item.tableNo.toString()
            }
        }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem === newItem
            }
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.itemName == newItem.itemName
            }
        }
    }
}
package com.example.dinein

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dinein.data.Item
import com.example.dinein.data.getFormattedPrice
import org.w3c.dom.Text

class BillingAdapter: ListAdapter<Item, BillingAdapter.BillViewHolder>(DiffCallback) {
    class BillViewHolder(private var view: View) : RecyclerView.ViewHolder(view){
        val itemName : TextView = view.findViewById(R.id.item_name)
        val itemPrice : TextView = view.findViewById(R.id.item_price)
        val itemQuantity : TextView = view.findViewById(R.id.item_quantity)
        val tableNo: TextView = view.findViewById(R.id.table_no)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id==newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_list_item,parent,false)
        return BillViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemName.text = item.itemName
        holder.itemPrice.text = item.itemPrice.toString()
        holder.itemQuantity.text = item.quantity.toString()
        holder.tableNo.text = item.tableNo.toString()
    }
}
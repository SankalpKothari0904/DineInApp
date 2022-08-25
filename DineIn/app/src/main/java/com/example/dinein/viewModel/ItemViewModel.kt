package com.example.dinein.viewModel

import androidx.lifecycle.*
import com.example.dinein.data.Item
import com.example.dinein.data.ItemDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ItemViewModel(private val itemDao: ItemDao): ViewModel() {
    val allMenuItems:LiveData<List<Item>> = itemDao.getItems().asLiveData()

    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()
    }

    fun deleteItemsAfterBilling(tableNum: Int){
        viewModelScope.launch { itemDao.delete(tableNum) }
    }

    fun retrieveItemsOrderedAtaTable(tableNo:Int): Flow<List<Item>> {
        return itemDao.getItemsFromTable(tableNo)
    }

    fun getBillingAmount(tableNo: Int): LiveData<Double>{
        return itemDao.getBillAmount(tableNo).asLiveData()
    }

    fun updateItem(itemId: Int, itemName: String, itemPrice: String, itemCount: String, tableNum: String) {
        val updatedItem = getUpdatedItemEntry(itemId, itemName, itemPrice, itemCount, tableNum)
        updateItem(updatedItem)
    }

    fun addNewItem(itemName: String, itemPrice: String, itemCount: String, tableNum: String) {
        val newItem = getNewItemEntry(itemName, itemPrice, itemCount,tableNum)
        insertItem(newItem)
    }

    private fun getNewItemEntry(itemName: String, itemPrice: String, itemCount: String, tableNum: String): Item {
        return Item(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantity = itemCount.toInt(),
            tableNo = tableNum.toInt()
        )
    }
    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String, tableNo: String): Boolean {
        if (itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank() || tableNo.isBlank()) {
            return false
        }
        return true
    }

    /**
     * Launching a new coroutine to update an item in a non-blocking way
     */
    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    private fun getUpdatedItemEntry(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String,
        tableNum: String
    ): Item {
        return Item(
            id = itemId,
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantity = itemCount.toInt(),
            tableNo = tableNum.toInt()
        )
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.deleteItem(item)
        }
    }
}

class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.dinein.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull @ColumnInfo(name = "name")
    val itemName: String,
    @NonNull @ColumnInfo(name = "price")
    val itemPrice: Double,
    @NonNull @ColumnInfo(name = "quantity")
    val quantity: Int,
    @NonNull @ColumnInfo(name = "tableNo")
    val tableNo: Int
)
/**
 * Returns the passed in price in currency format.
 */
fun Item.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(itemPrice)
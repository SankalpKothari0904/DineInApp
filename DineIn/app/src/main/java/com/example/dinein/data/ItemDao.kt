package com.example.dinein.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * from item ORDER BY tableNo")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from item WHERE tableNo = :tableNo")
    fun getItemsFromTable(tableNo: Int): Flow<List<Item>>

    @Query("Select sum(price * quantity) from item where tableNo = :tableNo")
    fun getBillAmount(tableNo: Int): Flow<Double>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("delete from item where tableNo = :tableNo")
    suspend fun delete(tableNo: Int)
}
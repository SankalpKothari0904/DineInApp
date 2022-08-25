package com.example.dinein

import android.app.Application
import com.example.dinein.data.ItemRoomDatabase

class MenuItemApplication:Application() {
    val database:ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}
package com.example.cupboardigi.data.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cupboardigi.data.model.dao.PostDao
import com.example.cupboardigi.data.model.data.item.ItemStorage
import com.example.cupboardigi.data.model.data.item.ItemType

@Database(
    entities = [
        ItemStorage::class,
        ItemType::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
package com.example.cupboardigi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cupboardigi.data.model.item.*
import com.example.cupboardigi.data.model.table.CrossRefItemScreen
import com.example.cupboardigi.database.dao.PostDao

@Database(
    entities = [
        ItemStorage::class,
        ItemType::class,
        ItemBoard::class,
        ItemStorageUser::class,
        ItemScreen::class,
        CrossRefItemScreen::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
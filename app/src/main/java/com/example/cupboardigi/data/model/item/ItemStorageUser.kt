package com.example.cupboardigi.data.model.item

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cupboardigi.data.model.item.ItemStorage

@Entity(tableName = "item_storage_user")
data class ItemStorageUser(
    @PrimaryKey
    @ColumnInfo(name = "id_item_user")
    val idItemUser: Long,
    @Embedded
    val idStorage: ItemStorage,
    @ColumnInfo(name = "id_screen_user")
    val screenID: Long
)
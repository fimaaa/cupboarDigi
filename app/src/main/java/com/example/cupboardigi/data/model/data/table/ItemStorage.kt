package com.example.cupboardigi.data.model.data.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_storage")
data class ItemStorage(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "type_item")
    val typeItem: Int,
    val image: String,
    @ColumnInfo(name = "name_item")
    val nameItem: String,
    @ColumnInfo(name = "volume_item")
    val volumeItem: Int,
    val qty: Int,
    @ColumnInfo(name = "qty_cupboard")
    val qtyCupBoard: Int
)
package com.example.cupboardigi.data.model.data.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_type")
data class ItemType(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "name_type")
    val nameType: String,
    @ColumnInfo(name = "form_item")
    val formItem: Int
)
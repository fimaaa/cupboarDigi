package com.example.cupboardigi.data.model.data.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_storage")
data class ItemStorage(
    @PrimaryKey
    val id: Long,
    val typeItem: Int,
    val image: String,
    val nameItem: String,
    val volumeItem: Int,
    val qty: Int,
    val qtyCupBoard: Int
)
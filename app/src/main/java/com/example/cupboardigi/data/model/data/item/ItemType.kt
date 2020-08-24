package com.example.cupboardigi.data.model.data.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemType(
    @PrimaryKey
    val id: Long,
    val nameType: String,
    val formItem: Int
)
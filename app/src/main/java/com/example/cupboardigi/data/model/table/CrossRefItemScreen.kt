package com.example.cupboardigi.data.model.table

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id_item_user", "id_screen"])
data class CrossRefItemScreen (
    @ColumnInfo(name = "id_item_user")
    val itemID: Long,
    @ColumnInfo(name = "id_screen")
    val screenID: Long,
    val qty: Int
)
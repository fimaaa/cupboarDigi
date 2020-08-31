package com.example.cupboardigi.data.model.item

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_series")
data class ItemSeries (
    @PrimaryKey
    @ColumnInfo(name = "id_series")
    val idSeries: Long,
    @ColumnInfo(name = "name_series")
    val nameSeries: String,
    @Embedded
    val typeItem: ItemType,
    val volume: Int,
    @ColumnInfo(name = "is_end")
    val isEnd: Boolean,
    val image: String
)
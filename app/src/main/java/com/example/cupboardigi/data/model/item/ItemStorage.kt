package com.example.cupboardigi.data.model.item

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_storage")
data class ItemStorage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_storage")
    val idStorage: Long,
    @Embedded
    val typeItem: ItemSeries,
    @ColumnInfo(name = "volume_item")
    val volumeItem: Int
)
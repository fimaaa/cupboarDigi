package com.example.cupboardigi.data.model.data.table

import androidx.room.Embedded
import androidx.room.Relation
import com.example.cupboardigi.data.model.data.table.ItemStorage
import com.example.cupboardigi.data.model.data.table.ItemType

data class ItemTypeWithItem (
    @Embedded
    val typeItem: ItemType,
    @Relation(
        parentColumn = "id",
        entityColumn = "type_item",
        entity = ItemStorage::class
    )
    val storageitem: List<ItemStorage>
)

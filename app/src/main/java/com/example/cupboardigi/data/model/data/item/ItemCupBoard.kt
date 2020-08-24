package com.example.cupboardigi.data.model.data.item

import androidx.room.Embedded
import androidx.room.Relation

data class ItemCupBoard (
    @Embedded
    val typeItem: ItemType,
    @Relation(
        parentColumn = "id",
        entityColumn = "typeItem",
        entity = ItemStorage::class
    )
    val storageitem: List<ItemStorage>
)

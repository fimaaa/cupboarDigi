package com.example.cupboardigi.data.model.table

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.cupboardigi.data.model.item.ItemScreen
import com.example.cupboardigi.data.model.item.ItemStorageUser

data class RelationItemInScreen (
    @Embedded val screen: ItemScreen,
    @Relation(
        parentColumn = "id_screen",
        entityColumn = "id_item_user"
    )
    val storageUser: List<ItemStorageUser>
)
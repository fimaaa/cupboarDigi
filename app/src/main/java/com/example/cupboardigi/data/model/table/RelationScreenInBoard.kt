package com.example.cupboardigi.data.model.table

import androidx.room.Embedded
import androidx.room.Relation
import com.example.cupboardigi.data.model.item.ItemBoard
import com.example.cupboardigi.data.model.item.ItemScreen

data class RelationScreenInBoard (
    @Embedded val board: ItemBoard,
    @Relation(
        parentColumn = "id_board",
        entityColumn = "id_board_in_screen"
    )
    val listScreen: List<ItemScreen>
)
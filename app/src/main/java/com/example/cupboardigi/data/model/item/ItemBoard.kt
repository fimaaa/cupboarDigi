package com.example.cupboardigi.data.model.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "item_board")
data class ItemBoard(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_board")
    val idBoard: Long,
    @ColumnInfo(name = "name_board")
    val nameBoard: String
){
    @Ignore
    var screen: List<ItemScreen>? = null
    constructor(
        idBoard: Long,
        nameBoard: String,
        screen: List<ItemScreen>
    ) : this(
        idBoard,
        nameBoard
    ) {
        this.screen = screen
    }
}
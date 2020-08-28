package com.example.cupboardigi.data.model.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_type")
data class ItemType(
    @PrimaryKey
    @ColumnInfo(name = "id_type")
    val idType: Long,
    @ColumnInfo(name = "name_type")
    val nameType: String,
    @ColumnInfo(name = "form_item")
    val formItem: Int
){
    enum class FormType(val form: Int) {
        UNKNOWN(0),
        BOOK_SMALL(1),
        BOOK_MEDIUM(2),
        BOOK_LARGE(3),
        PACKAGE_WIDTH_SMALL(4),
        PACKAGE_WIDTH_MEDIUM(5),
        PACKAGE_WIDTH_LARGE(6),
        PACKAGE_HEIGHT_SMALL(7),
        PACKAGE_HEIGHT_MEDIUM(8),
        PACKAGE_HEIGHT_LARGE(9)
    }
}
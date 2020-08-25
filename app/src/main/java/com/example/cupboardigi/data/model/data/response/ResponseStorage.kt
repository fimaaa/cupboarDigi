package com.example.cupboardigi.data.model.data.response

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey

object ResponseStorage {
    data class Response(
        val code: Int,
        val `data`: MutableList<Data>?,
        val success: Boolean,
        val message: String
    ){
        data class Data(
            val id: Long,
            @ColumnInfo(name = "type_item")
            val typeItem: Long,
            @ColumnInfo(name = "name_type")
            val nameType: String,
            @ColumnInfo(name = "form_item")
            val formItem: Int,
            val image: String,
            @ColumnInfo(name = "name_item")
            val nameItem: String,
            @ColumnInfo(name = "volume_item")
            val volumeItem: Int,
            val qty: Int,
            @ColumnInfo(name = "qty_cupboard")
            val qtyCupBoard: Int
        )
    }
}
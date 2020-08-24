package com.example.cupboardigi.data.model.data.response

import com.example.cupboardigi.data.model.data.item.ItemStorage

object ResponseStorage {
    data class Response(
        val code: Int,
        val `data`: MutableList<ItemStorage>?,
        val success: Boolean,
        val message: String
    )
}
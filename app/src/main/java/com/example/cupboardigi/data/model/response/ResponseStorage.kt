package com.example.cupboardigi.data.model.response

import com.example.cupboardigi.data.model.item.ItemStorage

object ResponseStorage {
    data class Response(
        val code: Int,
        val `data`: MutableList<ItemStorage>?,
        val success: Boolean,
        val message: String
    )
}
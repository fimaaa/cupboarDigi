package com.example.cupboardigi.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class Storage {
    data class Response(
        val code: Int,
        val `data`: MutableList<Data?>?,
        val success: Boolean,
        val message: String
    ) {
        @Parcelize
        data class Data(
            val idItem: String?,
            val typeItem: String?,
            val icon: String?
        ) : Parcelable
    }
}
package com.example.cupboardigi.ui.menu.board

import com.example.cupboardigi.data.model.item.ItemScreen

interface MenuBoardNavigator {
    fun addScreen()
    fun editScreen(
        position: Int,
        itemScreen: ItemScreen
    )
}
package com.example.cupboardigi.di.injection

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.cupboardigi.database.AppDatabase
import com.example.cupboardigi.ui.menu.board.MenuBoardViewModel

class ViewModelFactory(private val activity: FragmentActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuBoardViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "data").build()
            @Suppress("UNCHECKED_CAST")
            return MenuBoardViewModel(db.postDao(), activity.application) as T
        }
        @Suppress("UNCHECKED_CAST")
        return null as T
    }
}

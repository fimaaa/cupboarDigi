package com.example.cupboardigi.di.injection

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.cupboardigi.database.AppDatabase
import com.example.cupboardigi.ui.itemlist.ItemListViewModel
import com.example.cupboardigi.ui.menu.board.MenuBoardViewModel
import com.example.cupboardigi.ui.myitem.MyItemViewModel
import com.example.cupboardigi.ui.splash.SplashViewModel

class ViewModelFactory(private val activity: FragmentActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MenuBoardViewModel::class.java) -> {
                val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "data").build()
                @Suppress("UNCHECKED_CAST")
                return MenuBoardViewModel(db.postDao(), activity.application) as T
            }
            modelClass.isAssignableFrom(ItemListViewModel::class.java) -> {
                val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "data").build()
                @Suppress("UNCHECKED_CAST")
                return ItemListViewModel(db.postDao(), activity.application) as T
            }
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "data").build()
                @Suppress("UNCHECKED_CAST")
                return SplashViewModel(db.postDao(), activity.application) as T
            }
            modelClass.isAssignableFrom(MyItemViewModel::class.java) -> {
                val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "data").build()
                @Suppress("UNCHECKED_CAST")
                return MyItemViewModel(db.postDao(), activity.application) as T
            }
        }
        @Suppress("UNCHECKED_CAST")
        return null as T
    }
}

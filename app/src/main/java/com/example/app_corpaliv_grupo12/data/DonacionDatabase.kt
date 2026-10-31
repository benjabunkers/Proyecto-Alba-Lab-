package com.example.app_corpaliv_grupo12.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_corpaliv_grupo12.model.ContactoDonador
import com.example.app_corpaliv_grupo12.model.Donacion

@Database(entities = [Donacion::class, ContactoDonador::class], version = 1, exportSchema = false)
abstract class DonacionDatabase : RoomDatabase() {
    abstract fun donacionDao(): DonacionDao
    abstract fun contactoDonadorDao(): ContactoDonadorDao

    companion object {
        @Volatile private var INSTANCE: DonacionDatabase? = null
        fun get(context: Context): DonacionDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context.applicationContext, DonacionDatabase::class.java, "corpaliv_donaciones.db").build().also { INSTANCE = it }
        }
    }
}

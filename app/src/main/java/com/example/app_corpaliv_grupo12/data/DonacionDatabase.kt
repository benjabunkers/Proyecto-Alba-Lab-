package com.example.app_corpaliv_grupo12.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.app_corpaliv_grupo12.model.ContactoDonador
import com.example.app_corpaliv_grupo12.model.Donacion

@Database(entities = [Donacion::class, ContactoDonador::class], version = 2, exportSchema = false)
abstract class DonacionDatabase : RoomDatabase() {
    abstract fun donacionDao(): DonacionDao
    abstract fun contactoDonadorDao(): ContactoDonadorDao

    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE seguimiento_donadores ADD COLUMN fotoUri TEXT")
            }
        }
        @Volatile private var INSTANCE: DonacionDatabase? = null
        fun get(context: Context): DonacionDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context.applicationContext, DonacionDatabase::class.java, "corpaliv_donaciones.db").addMigrations(MIGRATION_1_2).build().also { INSTANCE = it }
        }
    }
}




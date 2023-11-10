package com.dicoding.doaseharihari.data.datamodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.doaseharihari.data.dao.DoaDao
import com.dicoding.doaseharihari.data.entity.DoaEntity

@Database(entities = [DoaEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun doaDao(): DoaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database_doa"
                    ).build()
                }
            }
            return INSTANCE as AppDatabase
        }
    }
}
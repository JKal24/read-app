package com.kal.bookreader.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kal.bookreader.data.dao.BookDao
import com.kal.bookreader.data.entity.Book

@Database(entities = [Book::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun BookDao() : BookDao

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null
        fun getAppDataBase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}
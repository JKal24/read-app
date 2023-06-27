package com.kal.bookreader.di

import android.content.Context
import androidx.room.Room
import com.kal.bookreader.data.AppDatabase
import com.kal.bookreader.data.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return AppDatabase.getAppDataBase(applicationContext)
    }
    @Provides
    @Singleton
    fun provideBookDao(appDatabase: AppDatabase): BookDao = appDatabase.BookDao()
}
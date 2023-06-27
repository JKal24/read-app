package com.kal.bookreader.di

import com.kal.bookreader.data.repository.BookRepositoryImpl
import com.kal.bookreader.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(bookRepositoryImpl: BookRepositoryImpl): BookRepository

}
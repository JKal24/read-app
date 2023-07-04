package com.kal.bookreader.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    val book_src : String,
    val book_name : String,
    val image_src : String
)
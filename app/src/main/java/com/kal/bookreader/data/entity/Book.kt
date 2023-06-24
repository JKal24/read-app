package com.kal.bookreader.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val uid : Int,
    val book_name : String,
    val book_description : String,
    val author : String,
    val imageSrc : String
)
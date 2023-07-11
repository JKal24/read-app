package com.kal.bookreader.data.dao

import androidx.room.*
import com.kal.bookreader.data.entity.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg books : Book)

    @Query("DELETE FROM books WHERE book_src = :bookSrc")
    suspend fun deleteBook(bookSrc : String)

    @Query("SELECT * FROM books ORDER BY book_name ASC")
    fun getBooks() : Flow<List<Book>>
}
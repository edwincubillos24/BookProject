package com.cubidevs.bookproject.local

import androidx.room.*

@Dao
interface BookDao {

    @Insert
    suspend fun saveBook(book: Book)

    @Query("SELECT * FROM table_book WHERE name LIKE :nameBook")
    suspend fun searchBook(nameBook: String): Book

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM table_book")
    suspend fun loadBooks(): MutableList<Book>

    @Update
    suspend fun updateBook(book: Book)

}
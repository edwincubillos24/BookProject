package com.cubidevs.bookproject.local.localrepository

import com.cubidevs.bookproject.BookProject
import com.cubidevs.bookproject.local.Book
import com.cubidevs.bookproject.local.BookDao
import java.sql.Types.NULL

class BookRepository {

    suspend fun saveBook(
        nameBook: String,
        author: String,
        pages: Int,
        resume: String,
        genre: String,
        score: Int,
        publicationDate: String
    ) {
        val book = Book(
            id = NULL,
            name = nameBook,
            author = author,
            pages = pages,
            resume = resume,
            genre = genre,
            score = score,
            publicationDate = publicationDate
        )

        val bookDao: BookDao = BookProject.database.BookDao()
        bookDao.saveBook(book)
    }

    suspend fun searchBook(nameBook: String): Book {
        val bookDao: BookDao = BookProject.database.BookDao()
        val book = bookDao.searchBook(nameBook)
        return book
    }

    suspend fun deleteBook(book: Book) {
        val bookDao: BookDao = BookProject.database.BookDao()
        bookDao.deleteBook(book)
    }

    suspend fun loadBooks(): ArrayList<Book> {
        val bookDao: BookDao = BookProject.database.BookDao()
        val booksList: ArrayList<Book> = bookDao.loadBooks() as ArrayList<Book>
        return booksList
    }

    suspend fun updateBook(book: Book) {
        val bookDao: BookDao = BookProject.database.BookDao()
        bookDao.updateBook(book)
    }
}
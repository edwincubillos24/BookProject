package com.cubidevs.bookproject.server.serverrepository

import com.cubidevs.bookproject.server.BookServer
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class BookServerRepository {

    val db = Firebase.firestore

    fun saveBook(
        nameBook: String,
        author: String,
        pages: Int,
        resume: String,
        genre: String,
        score: Int,
        publicationDate: String
    ) {

        val documentBook = db.collection("books").document()

        val book = BookServer(
            id = documentBook.id,
            name = nameBook,
            author = author,
            pages = pages,
            resume = resume,
            genre = genre,
            score = score,
            publicationDate = publicationDate
        )

        db.collection("books").document(documentBook.id).set(book)
    }

    fun searchBook(nameBook: String): BookServer? {
        var bookServerFound: BookServer? = null
        db.collection("books")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val bookServer: BookServer = document.toObject<BookServer>()
                    if (nameBook == bookServer.name) {
                        bookServerFound = bookServer
                    }
                }
            }
        return bookServerFound
    }

    fun deleteBook(book: BookServer) {
        book.id?.let { id ->
            db.collection("books")
                .document(id)
                .delete()
        }

    }
}
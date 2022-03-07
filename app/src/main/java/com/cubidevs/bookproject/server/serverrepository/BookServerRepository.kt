package com.cubidevs.bookproject.server.serverrepository

import com.cubidevs.bookproject.server.BookServer
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

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

    suspend fun loadBooks(): QuerySnapshot {
        return withContext(Dispatchers.IO) {
            db.collection("books").get().await()
        }
    }

    fun deleteBook(book: BookServer) {
        book.id?.let { id ->
            db.collection("books")
                .document(id)
                .delete()
        }
    }
}

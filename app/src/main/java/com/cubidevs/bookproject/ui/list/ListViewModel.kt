package com.cubidevs.bookproject.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cubidevs.bookproject.local.Book
import com.cubidevs.bookproject.local.localrepository.BookRepository
import com.cubidevs.bookproject.server.BookServer
import com.cubidevs.bookproject.server.serverrepository.BookServerRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val bookRepository = BookRepository()
    val bookServerRepository = BookServerRepository()

    private var booksList: ArrayList<BookServer> = ArrayList()

    private val loadBooks : MutableLiveData<ArrayList<Book>> = MutableLiveData()
    val loadBooksDone: LiveData<ArrayList<Book>> = loadBooks

    private val loadBooksFromServer : MutableLiveData<ArrayList<BookServer>> = MutableLiveData()
    val loadBooksFromServerDone: LiveData<ArrayList<BookServer>> = loadBooksFromServer

    fun loadBooks() {
        GlobalScope.launch(Dispatchers.IO) {
            loadBooks.postValue(bookRepository.loadBooks())
        }
    }

    fun loadBooksFromServer() {
        GlobalScope.launch(Dispatchers.IO){
            val querySnapshot = bookServerRepository.loadBooks()
            for (document in querySnapshot) {
                val bookServer: BookServer = document.toObject<BookServer>()
                booksList.add(bookServer)
            }
            loadBooksFromServer.postValue(booksList)
        }
    }
}
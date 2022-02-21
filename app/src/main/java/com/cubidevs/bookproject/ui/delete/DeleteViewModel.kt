package com.cubidevs.bookproject.ui.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cubidevs.bookproject.local.Book
import com.cubidevs.bookproject.local.localrepository.BookRepository
import com.cubidevs.bookproject.server.BookServer
import com.cubidevs.bookproject.server.serverrepository.BookServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteViewModel : ViewModel() {

    val bookRepository = BookRepository()
    val bookServerRepository = BookServerRepository()

    private val findBook: MutableLiveData<Book> = MutableLiveData()
    val findBookDone: LiveData<Book> = findBook

    private val findBookServer: MutableLiveData<BookServer?> = MutableLiveData()
    val findBookServerDone: LiveData<BookServer?> = findBookServer

    fun searchBook(nameBook: String) {
        GlobalScope.launch(Dispatchers.IO) {
            //findBook.postValue(bookRepository.searchBook(nameBook))
            findBookServer.postValue(bookServerRepository.searchBook(nameBook))
        }
    }

    fun deleteBook(book: Book) {
        GlobalScope.launch(Dispatchers.IO) {
            bookRepository.deleteBook(book)
        }
    }

    fun deleteBookServer(book: BookServer) {
        GlobalScope.launch(Dispatchers.IO) {
            bookServerRepository.deleteBook(book)
        }
    }
}
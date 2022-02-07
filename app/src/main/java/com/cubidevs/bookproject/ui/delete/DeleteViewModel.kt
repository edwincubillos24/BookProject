package com.cubidevs.bookproject.ui.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cubidevs.bookproject.local.Book
import com.cubidevs.bookproject.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteViewModel : ViewModel() {

    val bookRepository = BookRepository()

    private val findBook: MutableLiveData<Book> = MutableLiveData()
    val findBookDone: LiveData<Book> = findBook

    fun searchBook(nameBook: String) {
        GlobalScope.launch(Dispatchers.IO) {
            findBook.postValue(bookRepository.searchBook(nameBook))
        }
    }

    fun deleteBook(book: Book) {
        GlobalScope.launch(Dispatchers.IO) {
            bookRepository.deleteBook(book)
        }
    }
}
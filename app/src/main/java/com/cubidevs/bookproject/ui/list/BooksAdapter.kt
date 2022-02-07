package com.cubidevs.bookproject.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cubidevs.bookproject.R
import com.cubidevs.bookproject.databinding.CardViewItemBookBinding
import com.cubidevs.bookproject.local.Book

class BooksAdapter(
    private val booksList: ArrayList<Book>
) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = booksList[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = booksList.size


    fun appendItems(newList: ArrayList<Book>) {
        booksList.clear()
        booksList.addAll(newList)
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewItemBookBinding.bind(itemView)
        fun bind(book: Book) {
            with(binding){
                nameBookTextView.text = book.name
                authorTextView.text = book.author
            }
        }
    }
}
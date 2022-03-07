package com.cubidevs.bookproject.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cubidevs.bookproject.R
import com.cubidevs.bookproject.databinding.CardViewItemBookBinding
import com.cubidevs.bookproject.server.BookServer
import com.squareup.picasso.Picasso

class BooksAdapter(
    private val booksList: ArrayList<BookServer>,
    private val onItemClicked: (BookServer) -> Unit
) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = booksList[position]
        holder.bind(book)
        holder.itemView.setOnClickListener { onItemClicked(booksList[position]) }
    }

    override fun getItemCount(): Int = booksList.size

    fun appendItems(newList: ArrayList<BookServer>) {
        booksList.clear()
        booksList.addAll(newList)
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewItemBookBinding.bind(itemView)
        private val context = binding.root
        fun bind(book: BookServer) {
            with(binding) {
                nameBookTextView.text = book.name
                authorTextView.text = book.author
                //     Glide.with(context).load(book.urlPicture).into(pictureBookImageView)
                Picasso.get().load(book.urlPicture).into(pictureBookImageView)
            }
        }
    }
}

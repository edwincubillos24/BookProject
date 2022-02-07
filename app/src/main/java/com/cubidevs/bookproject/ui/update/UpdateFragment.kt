package com.cubidevs.bookproject.ui.update

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.cubidevs.bookproject.R
import com.cubidevs.bookproject.databinding.FragmentUpdateBinding
import com.cubidevs.bookproject.local.Book

class UpdateFragment : Fragment() {

    private lateinit var updateBinding: FragmentUpdateBinding
    private lateinit var updateViewModel: UpdateViewModel
    private lateinit var book: Book

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        updateViewModel = ViewModelProvider(this)[UpdateViewModel::class.java]
        return updateBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateViewModel.findBookDone.observe(viewLifecycleOwner, {result ->
            onFindBookDoneSubscribe(result)
        })

        with(updateBinding){
            searchButton.setOnClickListener {
                updateViewModel.searchBook(nameEditText.text.toString())
            }

            updateButton.setOnClickListener{
                book.name = nameEditText.text.toString()
                book.author = nameAuthorEditText.text.toString()
                book.pages = pagesEditText.text.toString().toInt()
                book.resume = abstractEditText.text.toString()

                updateViewModel.updateBook(book)
            }
        }
    }

    private fun onFindBookDoneSubscribe(book: Book) {
        this.book = book
        with(updateBinding){
            updateFormLayout.isVisible = true
            searchButton.isVisible = false
            updateButton.isVisible = true
            nameAuthorEditText.setText(book.author)
            pagesEditText.setText(book.pages.toString())
            abstractEditText.setText(book.resume)
            publicationDateButton.text = book.publicationDate
            when (book.score) {
                1 -> oneRadioButton.isChecked = true
                2 -> twoRadioButton.isChecked = true
                3 -> threeRadioButton.isChecked = true
                4 -> fourRadioButton.isChecked = true
                else -> fiveRadioButton.isChecked = true
            }
        }
    }
}
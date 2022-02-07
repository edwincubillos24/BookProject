package com.cubidevs.bookproject

import android.app.Application
import androidx.room.Room
import com.cubidevs.bookproject.local.BookDatabase

class BookProject : Application() {

    companion object {
        lateinit var database: BookDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            BookDatabase::class.java,
            "book_db"
        ).build()
    }
}
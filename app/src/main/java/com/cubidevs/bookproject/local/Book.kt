package com.cubidevs.bookproject.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Types.NULL
import java.io.Serializable

@Entity(tableName= "table_book")
data class Book(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = NULL,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "author") var author: String = "",
    @ColumnInfo(name = "pages") var pages: Int = 0,
    @ColumnInfo(name = "resume") var resume: String = "",
    @ColumnInfo(name = "genre") var genre: String = "",
    @ColumnInfo(name = "score") var score: Int = 0,
    @ColumnInfo(name = "publicationDate") var publicationDate: String = ""
) : Serializable


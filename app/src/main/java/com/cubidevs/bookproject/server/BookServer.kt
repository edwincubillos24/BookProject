package com.cubidevs.bookproject.server

data class BookServer(
    var id: String? = null,
    var name: String? = null,
    var author: String? = null,
    var pages: Int? = null,
    var resume: String? = null,
    var genre: String? = null,
    var score: Int? = null,
    var publicationDate: String? = null
)
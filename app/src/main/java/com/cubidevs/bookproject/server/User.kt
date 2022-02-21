package com.cubidevs.bookproject.server

data class User(
    var uid: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var city: String? = null,
    var role: Role? = null
)

enum class Role {
    VENDEDOR, COMPRADOR, AMBOS
}



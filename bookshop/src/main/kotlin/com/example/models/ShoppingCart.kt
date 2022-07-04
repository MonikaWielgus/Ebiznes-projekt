package com.example.models

import kotlinx.serialization.Serializable

@Serializable
class ShoppingCart (
    val clientId: Int,
    val products: MutableMap<Book, Int>
)
package com.example.models

import kotlinx.serialization.Serializable

@Serializable
class ShoppingCart (
    val clientId: String,
    val products: MutableList<BookWithAmount>
)
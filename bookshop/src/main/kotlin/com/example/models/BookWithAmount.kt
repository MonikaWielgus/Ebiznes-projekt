package com.example.models

import kotlinx.serialization.Serializable

@Serializable
class BookWithAmount (
    val book: Book, var amount: Int
)
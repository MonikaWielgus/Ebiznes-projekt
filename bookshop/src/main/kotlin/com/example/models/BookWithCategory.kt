package com.example.models

import kotlinx.serialization.Serializable

@Serializable
class BookWithCategory (
    val id: Int, val title: String, val author: String, val category: String?, val price: Double
        )
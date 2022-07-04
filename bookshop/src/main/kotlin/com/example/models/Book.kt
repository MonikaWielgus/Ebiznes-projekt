package com.example.models

import kotlinx.serialization.Serializable

@Serializable
class Book (
    val id: Int,
    val title: String,
    val author: String,
    val categoryId: Int,
    val price: Double
    )
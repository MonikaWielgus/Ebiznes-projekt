package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Series (
    val id: Int,
    val name: String,
    val numberOfBooks: Int
    )
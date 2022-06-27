package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Author (
    val id: Int,
    val name: String,
    val surname: String
    )
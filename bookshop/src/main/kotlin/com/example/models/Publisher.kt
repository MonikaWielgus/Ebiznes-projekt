package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Publisher (
    val id: Int,
    val name: String,
    val country: String
    )
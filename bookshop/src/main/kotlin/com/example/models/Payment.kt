package com.example.models

@kotlinx.serialization.Serializable
data class Payment (
    val creditCardTokenId: String,
    val value: Int
    )

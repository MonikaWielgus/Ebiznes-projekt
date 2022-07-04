package com.example.models

import kotlinx.serialization.Serializable

@Serializable
class Book ( val id: Int, val title: String, val author: String, val categoryId: Int, val price: Double) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (id != other.id) return false
        if (title != other.title) return false
        if (author != other.author) return false
        if (categoryId != other.categoryId) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + categoryId
        result = 31 * result + price.hashCode()
        return result
    }
}
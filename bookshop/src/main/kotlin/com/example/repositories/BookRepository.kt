package com.example.repositories

import com.example.models.*

class BookRepository {
    companion object{
        fun getBook(id: Int) : Book? {
            return bookStorage[id]
        }

        fun getBooksList() : List<Book> {
            return bookStorage.values.toList()
        }
    }
}

val bookStorage = mutableMapOf(
    1 to Book(
        1,
        "Jakaś książka",
        "Jakiś autor",
        1,
        11.59
    ),
    2 to Book(
        2,
        "Jakaś książka 2",
        "Jakiś autor 2",
        1,
        20.30
    ),
    3 to Book(
        3,
        "Kryminal",
        "Jakiś autor 2",
        2,
        20.30
    )
)
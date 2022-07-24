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
        "Książka 1",
        "Autor 1",
        1,
        11.59
    ),
    2 to Book(
        2,
        "Książka 2",
        "Autor 2",
        1,
        20.30
    ),
    3 to Book(
        3,
        "Książka 2",
        "Autor 2",
        2,
        20.30
    )
)
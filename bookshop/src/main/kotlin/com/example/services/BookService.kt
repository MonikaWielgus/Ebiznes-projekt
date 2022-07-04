package com.example.services

import com.example.models.Book
import com.example.repositories.BookRepository

class BookService {
    companion object{
        fun getBook(id: Int) : Book? {
            return BookRepository.getBook(id);
        }

        fun getBooksList() : List<Book> {
            return BookRepository.getBooksList()
        }
    }
}
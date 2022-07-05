package com.example.services

import com.example.models.Book
import com.example.models.BookWithCategory
import com.example.repositories.BookRepository

class BookService {
    companion object{

        fun getBookWithCategory(id: Int) : BookWithCategory? {
            val book = BookRepository.getBook(id)
            if (book != null) {
                val category = CategoryService.getCategory(book.categoryId)
                return BookWithCategory(book.id, book.title, book.author, category?.name, book.price)
            }
            return null
        }

        fun getBook(id: Int) : Book? {
            return BookRepository.getBook(id)
        }

        fun getBooksList() : List<Book> {
            return BookRepository.getBooksList()
        }
    }
}
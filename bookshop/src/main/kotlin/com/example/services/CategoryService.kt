package com.example.services

import com.example.models.Book
import com.example.models.Category
import com.example.repositories.CategoryRepository

class CategoryService {
    companion object{
        fun getBooksFromCategory(id: Int) : List<Book> {
            val result = mutableListOf<Book>();
            val books = BookService.getBooksList()
            for(book in books) {
                if(book.categoryId == id) {
                    result.add(book)
                }
            }
            return result
        }

        fun getCategoryList() : List<Category> {
            return CategoryRepository.getCategoryList();
        }
    }
}
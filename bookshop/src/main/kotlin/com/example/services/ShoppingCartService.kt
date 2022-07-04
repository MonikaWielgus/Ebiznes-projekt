package com.example.services

import com.example.models.ShoppingCart
import com.example.repositories.ShoppingCartRepository

class ShoppingCartService {
    companion object{

        fun addToCart(clientId: Int, bookId: Int) {
            val cart = ShoppingCartRepository.getShoppingCart(clientId)
            val book = BookService.getBook(bookId)
            if (book != null) {
                cart?.products?.add(book)
            }
        }

        fun getShoppingCart(clientId: Int) : ShoppingCart? {
            return ShoppingCartRepository.getShoppingCart(clientId)
        }
    }
}
package com.example.services

import com.example.models.ShoppingCart
import com.example.repositories.ShoppingCartRepository

class ShoppingCartService {
    companion object{

        fun addToCart(clientId: Int, bookId: Int) {
            val cart = ShoppingCartRepository.getShoppingCart(clientId)
            val book = BookService.getBook(bookId)
            if (book != null) {
                val numberOfItems = cart?.products?.get(book)
                if (numberOfItems == null) {
                    cart?.products?.put(book, 1)
                }
                else {
                    cart.products[book] = numberOfItems+1
                }
            }
        }

        fun removeFromCart(clientId: Int, bookId: Int) {
            val cart = ShoppingCartRepository.getShoppingCart(clientId)
            val book = BookService.getBook(bookId)
            if (book != null) {
                cart?.products?.remove(book)
            }
        }

        fun getShoppingCart(clientId: Int) : ShoppingCart? {
            return ShoppingCartRepository.getShoppingCart(clientId)
        }
    }
}
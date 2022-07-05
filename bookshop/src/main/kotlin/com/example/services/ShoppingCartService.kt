package com.example.services

import com.example.models.BookWithAmount
import com.example.models.ShoppingCart
import com.example.repositories.ShoppingCartRepository

class ShoppingCartService {
    companion object{

        fun addToCart(clientId: Int, bookId: Int) {
            val cart = ShoppingCartRepository.getShoppingCart(clientId)
            val book = BookService.getBook(bookId)
            if (book != null) {
                for (bookWithAmount in cart?.products!!) {
                    if (bookWithAmount.book.id == book.id) {
                        val numberOfItems = bookWithAmount.amount
                        bookWithAmount.amount = numberOfItems+1
                        return
                    }
                }
                cart.products.add(BookWithAmount(book, 1))
            }
        }

        fun removeFromCart(clientId: Int, bookId: Int) {
            val cart = ShoppingCartRepository.getShoppingCart(clientId)
            val book = BookService.getBook(bookId)
            if (book != null) {
                for (bookWithAmount in cart?.products!!) {
                    if (bookWithAmount.book.id == book.id) {
                        cart.products.remove(bookWithAmount)
                    }
                }
            }
        }

        fun getShoppingCart(clientId: Int) : ShoppingCart? {
            return ShoppingCartRepository.getShoppingCart(clientId)
        }
    }
}
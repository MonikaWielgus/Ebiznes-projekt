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

        fun replaceInCart(clientId: Int, bookId: Int, amount: Int) {
            val cart = ShoppingCartRepository.getShoppingCart(clientId)
            val book = BookService.getBook(bookId)
            if (book != null) {
                for (bookWithAmount in cart?.products!!) {
                    if (bookWithAmount.book.id == book.id) {
                        if (amount <= 0) {
                            cart.products.remove(bookWithAmount)
                        }
                        else {
                            bookWithAmount.amount = amount
                        }
                        return
                    }
                }
            }
        }

        fun removeFromCart(clientId: Int, bookId: Int) {
            val cart = ShoppingCartRepository.getShoppingCart(clientId)
            val book = BookService.getBook(bookId)
            if (book != null) {
                for (bookWithAmount in cart?.products!!) {
                    if (bookWithAmount.book.id == book.id) {
                        cart.products.remove(bookWithAmount)
                        return
                    }
                }
            }
        }

        fun removeAllFromCart(clientId: Int) {
            ShoppingCartRepository.removeAllFromShoppingCart(clientId)
        }

        fun getShoppingCart(clientId: Int) : ShoppingCart? {
            return ShoppingCartRepository.getShoppingCart(clientId)
        }
    }
}
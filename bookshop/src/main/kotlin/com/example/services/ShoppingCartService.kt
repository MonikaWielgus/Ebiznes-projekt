package com.example.services

import com.example.models.BookWithAmount
import com.example.models.ShoppingCart
import com.example.repositories.ShoppingCartRepository

class ShoppingCartService {
    companion object{

        fun addToCart(sessionId: String, bookId: Int) {
            val userId = UserInfoService.getUserId(sessionId)
            val cart = ShoppingCartRepository.getShoppingCart(userId)
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

        fun replaceInCart(sessionId: String, bookId: Int, amount: Int) {
            val userId = UserInfoService.getUserId(sessionId)
            val cart = ShoppingCartRepository.getShoppingCart(userId)
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

        fun removeFromCart(sessionId: String, bookId: Int) {
            val userId = UserInfoService.getUserId(sessionId)
            val cart = ShoppingCartRepository.getShoppingCart(userId)
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

        fun removeAllFromCart(sessionId: String) {
            val userId = UserInfoService.getUserId(sessionId)
            ShoppingCartRepository.removeAllFromShoppingCart(userId)
        }

        fun getShoppingCart(sessionId: String) : ShoppingCart? {
            val userId = UserInfoService.getUserId(sessionId)
            return ShoppingCartRepository.getShoppingCart(userId)
        }

        fun addCartIfNotExists(sessionId: String) {
            val userId = UserInfoService.getUserId(sessionId)
            val cart = ShoppingCartRepository.getShoppingCart(userId)
            if (cart == null) {
                ShoppingCartRepository.addShoppingCart(userId)
            }
        }
    }
}
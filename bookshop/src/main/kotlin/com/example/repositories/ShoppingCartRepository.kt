package com.example.repositories

import com.example.models.Book
import com.example.models.ShoppingCart

class ShoppingCartRepository {
    companion object{

        fun getShoppingCart(clientId: Int) : ShoppingCart? {
            return shoppingCartStorage[clientId]
        }

        fun addShoppingCart(clientId: Int) {
            shoppingCartStorage[clientId] = ShoppingCart(clientId, mutableMapOf())
        }
    }
}

//val shoppingCartRepository = mutableMapOf<Int, ShoppingCart>();
val shoppingCartStorage = mutableMapOf(1 to ShoppingCart(1, mutableMapOf()));
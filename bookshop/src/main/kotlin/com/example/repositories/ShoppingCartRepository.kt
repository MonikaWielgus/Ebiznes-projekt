package com.example.repositories

import com.example.models.ShoppingCart

class ShoppingCartRepository {
    companion object{

        fun getShoppingCart(clientId: String) : ShoppingCart? {
            return shoppingCartRepository[clientId]
        }

        fun removeAllFromShoppingCart(clientId: String) {
            shoppingCartRepository[clientId] = ShoppingCart(clientId, mutableListOf())
        }

        fun addShoppingCart(clientId: String) {
            shoppingCartRepository[clientId] = ShoppingCart(clientId, mutableListOf())
        }
    }
}

val shoppingCartRepository = mutableMapOf<String, ShoppingCart>()
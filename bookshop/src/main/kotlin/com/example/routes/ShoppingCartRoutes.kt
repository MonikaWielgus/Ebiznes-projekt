package com.example.routes

import com.example.plugins.UserSession
import com.example.services.ShoppingCartService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.shoppingCartRoutes() {
    route("/cart") {
        get {
            val userSession = call.sessions.get<UserSession>()
            if (userSession == null) {
                call.respond(HttpStatusCode.Forbidden)
            }
            else {
                val cart = ShoppingCartService.getShoppingCart(userSession.id)
                if (cart != null) {
                    call.respond(cart)
                }
                else {
                    call.respondText("Shopping cart not found", status = HttpStatusCode.OK)
                }
            }
        }
        post {
            val userSession = call.sessions.get<UserSession>()
            if (userSession == null) {
                call.respond(HttpStatusCode.Forbidden)
            }
            else {
                val bookId = call.receive<Int>()
                ShoppingCartService.addToCart(userSession.id, bookId)
                call.respondText("Book added correctly", status = HttpStatusCode.Created)
            }
        }
        post("{amount}") {
            val userSession = call.sessions.get<UserSession>()
            if (userSession == null) {
                call.respond(HttpStatusCode.Forbidden)
            }
            else {
                val amount = call.parameters["amount"] ?: return@post call.respondText(
                    "Missing amount",
                    status = HttpStatusCode.BadRequest
                )
                val bookId = call.receive<Int>()
                ShoppingCartService.replaceInCart(userSession.id, bookId, amount.toInt())
                call.respondText("Book added correctly", status = HttpStatusCode.Created)
            }
        }
        delete {
            val userSession = call.sessions.get<UserSession>()
            if (userSession == null) {
                call.respond(HttpStatusCode.Forbidden)
            }
            else {
                val bookId = call.receive<Int>()
                ShoppingCartService.removeFromCart(userSession.id, bookId)
                call.respondText("Book removed correctly", status = HttpStatusCode.Created)
            }
        }
        delete("all") {
            val userSession = call.sessions.get<UserSession>()
            if (userSession == null) {
                call.respond(HttpStatusCode.Forbidden)
            }
            else {
                ShoppingCartService.removeAllFromCart(userSession.id)
                call.respondText("All removed correctly", status = HttpStatusCode.Created)
            }
        }
    }
}
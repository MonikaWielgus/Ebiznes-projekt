package com.example.routes

import com.example.services.ShoppingCartService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.shoppingCartRoutes() {
    route("/cart") {
        get("{clientId}") {
            val clientId = call.parameters["clientId"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val cart = ShoppingCartService.getShoppingCart(clientId.toInt())
            if (cart != null) {
                call.respond(cart)
            }
            else {
                call.respondText("Shopping cart not found", status = HttpStatusCode.OK)
            }
        }
        post("{clientId}") {
            val clientId = call.parameters["clientId"] ?: return@post call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val bookId = call.receive<Int>()
            ShoppingCartService.addToCart(clientId.toInt(), bookId);
            call.respondText("Book added correctly", status = HttpStatusCode.Created)
        }
    }
}
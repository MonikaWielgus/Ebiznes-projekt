package com.example.routes

import com.example.plugins.UserSession
import com.example.services.ShoppingCartService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.shoppingCartRoutes() {
    route("/cart") {
        get("{clientId}") {
            when (val userSession: UserSession? = call.sessions.get()) {
                null -> {
                    call.respond(HttpStatusCode.Forbidden)
                }
                else -> {
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
        post("{clientId}/{amount}") {
            val clientId = call.parameters["clientId"] ?: return@post call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val amount = call.parameters["amount"] ?: return@post call.respondText(
                "Missing amount",
                status = HttpStatusCode.BadRequest
            )
            val bookId = call.receive<Int>()
            ShoppingCartService.replaceInCart(clientId.toInt(), bookId, amount.toInt());
            call.respondText("Book added correctly", status = HttpStatusCode.Created)
        }
        delete("{clientId}") {
            val clientId = call.parameters["clientId"] ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val bookId = call.receive<Int>()
            ShoppingCartService.removeFromCart(clientId.toInt(), bookId);
            call.respondText("Book removed correctly", status = HttpStatusCode.Created)
        }
        delete("{clientId}/all") {
            val clientId = call.parameters["clientId"] ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            ShoppingCartService.removeAllFromCart(clientId.toInt());
            call.respondText("All removed correctly", status = HttpStatusCode.Created)
        }
    }
}
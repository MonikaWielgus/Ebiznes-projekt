package com.example.routes

import com.example.services.BookService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.bookRoutes() {
    route("/books") {
        get {
            call.respond(BookService.getBooksList())
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val book = BookService.getBook(id.toInt())
            if (book != null) {
                call.respond(book)
            }
            else {
                call.respondText("Book not found", status = HttpStatusCode.OK)
            }
        }
        get("{id}/withCategory") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val book = BookService.getBookWithCategory(id.toInt())
            if (book != null) {
                call.respond(book)
            }
            else {
                call.respondText("Book not found", status = HttpStatusCode.OK)
            }
        }
    }
}
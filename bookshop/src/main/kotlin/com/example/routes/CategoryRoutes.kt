package com.example.routes

import com.example.services.CategoryService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.categoryRoutes() {
    route("/categories") {
        get {
            call.respond(CategoryService.getCategoryList())
        }
        get("{id}/books") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            call.respond(CategoryService.getBooksFromCategory(id.toInt()))
        }
    }
}
package com.example.plugins

import com.example.routes.bookRoutes
import com.example.routes.categoryRoutes
import com.example.routes.shoppingCartRoutes
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        categoryRoutes()
        bookRoutes()
        shoppingCartRoutes()
    }
}

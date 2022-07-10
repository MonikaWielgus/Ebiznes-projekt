package com.example.plugins

import com.example.security.configureAuthentication
import com.example.security.configureCORS
import com.example.security.configureSecurityRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureSecurity() {

    configureSessions()
    configureAuthentication()
    configureCORS()
    routing {
        route("/api") {
            configureSecurityRoutes()
        }
    }
}



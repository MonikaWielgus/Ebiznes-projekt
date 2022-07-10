package com.example.security

import com.example.clients.applicationHttpClient
import com.example.plugins.UserSession
import io.ktor.client.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.p

fun Route.configureSecurityRoutes(httpClient: HttpClient = applicationHttpClient) {
    get("") {
        call.respondHtml {
            body {
                p {
                    a("/auth") { +"Login with Google" }
                }
            }
        }
    }
    authenticate("auth-oauth-google") {
        get("/auth") {
            // Redirects to 'authorizeUrl' automatically
        }
        get("/callback") {
            val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
            call.sessions.set(UserSession(principal?.accessToken.toString(), 0))
            call.respondRedirect("http://localhost:3000/")
        }
    }

    get("/auth/logout") {
        if (call.sessions.get<UserSession>() != null) {
            call.sessions.clear<UserSession>()
            call.respondRedirect("http://localhost:3000/")
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }
}
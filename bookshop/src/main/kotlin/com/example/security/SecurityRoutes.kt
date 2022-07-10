package com.example.security

import com.example.clients.applicationHttpClient
import com.example.plugins.UserInfo
import com.example.plugins.UserSession
import com.example.services.ShoppingCartService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
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
            call.respondRedirect("http://localhost:3000/")
        }
        get("/callback") {
            val principal: OAuthAccessTokenResponse.OAuth2? = call.authentication.principal()
            call.sessions.set(UserSession(principal?.accessToken.toString(), 0))
            call.respondRedirect("http://localhost:3000/")
        }
    }

    get("/auth/hello") {
        val userSession: UserSession? = call.sessions.get()
        if (userSession != null) {
            val userInfo: UserInfo = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${userSession.id}")
                }
            }.body()
            call.respondText("Hello, ${userInfo.name}!")
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }

    get("/auth/info") {
        val userSession: UserSession? = call.sessions.get()
        if (userSession != null) {
            val userInfo: UserInfo = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${userSession.id}")
                }
            }.body()
            call.respond(userInfo)
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }

    get("/auth/check") {
        if (call.sessions.get<UserSession>() != null) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.Forbidden)
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

    authenticate("auth-oauth-github") {
        get("/auth_github") {
            call.respondRedirect("/callback_github")
        }

        get("/callback_github") {
            val principal: OAuthAccessTokenResponse.OAuth2? = call.authentication.principal()
            call.sessions.set(UserSession(principal?.accessToken.toString(), 0))
            call.respondRedirect("http://localhost:3000/")
        }
    }
}
package com.example.routes

import com.example.models.Payment
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.paymentRoutes() {
    route("/payment") {
        post("/charge") {
            val payment = call.receive<Payment>()

            val client = HttpClient()
            val dotenv = dotenv()
            val secret = dotenv["STRIPE_SECRET_KEY"]
            val httpResponse: HttpResponse = client.post("https://api.stripe.com/v1/payment_intents") {
                headers {
                    append(HttpHeaders.ContentType, "application/x-www-form-urlencoded")
                    append(HttpHeaders.Authorization, "Bearer $secret")
                }
                setBody(FormDataContent(Parameters.build {
                    append("amount", (payment.value).toString())
                    append("currency", "PLN")
                    append("source", payment.creditCardTokenId)
                }))
            }
            if (httpResponse.status == HttpStatusCode.OK) {
                call.respondText("Payment done", status = HttpStatusCode.OK)
            }
        }
    }

}
package com.example.services

import com.example.clients.applicationHttpClient
import com.example.models.GithubUserInfo
import com.example.models.GoogleUserInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking

class UserInfoService {

    companion object {
        private val httpClient: HttpClient = applicationHttpClient

        private fun getUserInfoFromGoogle(sessionId: String): GoogleUserInfo? {
            var isGoogle = false
            runBlocking {
                val client = HttpClient()
                val httpResponse: HttpResponse = client.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $sessionId")
                    }
                }
                if (httpResponse.status == HttpStatusCode.OK) {
                    isGoogle = true
                }
            }
            return if (isGoogle) {
                runBlocking {
                    httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                        headers {
                            append(HttpHeaders.Authorization, "Bearer $sessionId")
                        }
                    }.body()
                }
            } else {
                null
            }
        }

        private fun getUserInfoFromGithub(sessionId: String): GithubUserInfo? {
            var isGithub = false
            runBlocking {
                val client = HttpClient()
                val httpResponse: HttpResponse = client.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $sessionId")
                    }
                }
                if (httpResponse.status == HttpStatusCode.OK) {
                    isGithub = true
                }
            }
            return if (isGithub) {
                runBlocking {
                    httpClient.get("https://api.github.com/user") {
                        headers {
                            append(HttpHeaders.Authorization, "Bearer $sessionId")
                        }
                    }.body()
                }
            } else {
                null
            }
        }

        fun getUserId(sessionId: String): String {
            val googleUserInfo = getUserInfoFromGoogle(sessionId)
            if (googleUserInfo != null) {
                return googleUserInfo.id
            }
            val githubUserInfo = getUserInfoFromGithub(sessionId)
            return githubUserInfo?.id.toString()
        }
    }
}

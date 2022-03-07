package com.example.test_kotlin.api

import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("posts")
    suspend fun getQuotes(): Response<QuoteList>
}
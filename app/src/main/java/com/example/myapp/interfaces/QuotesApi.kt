package com.example.myapp.interfaces

import com.example.myapp.model.Items
import com.example.myapp.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("/products")
    suspend fun getQuotes(): Response<Items>
}
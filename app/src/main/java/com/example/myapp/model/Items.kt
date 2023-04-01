package com.example.myapp.model

data class Items(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
):java.io.Serializable

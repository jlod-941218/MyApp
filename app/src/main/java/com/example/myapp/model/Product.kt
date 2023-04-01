package com.example.myapp.model


data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountingPercentage: Double,
    val rating: Double,
    val stock: Double,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
):java.io.Serializable

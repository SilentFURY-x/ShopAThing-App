package com.fury.shopathing.data.remote

import com.fury.shopathing.domain.model.Product

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val images: List<String>,
    val category: String, // DummyJSON sends category as a String!
    val rating: Double = 0.0
)

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        // DummyJSON images work perfectly, just take the first one
        imageUrl = images.firstOrNull() ?: "",
        rating = rating

    )
}
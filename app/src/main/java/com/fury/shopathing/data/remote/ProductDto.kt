package com.fury.shopathing.data.remote

import com.google.gson.annotations.SerializedName
import com.fury.shopathing.domain.model.Product

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    // The API returns a list of image strings, we just want the first one
    val images: List<String>,
    val category: CategoryDto
)

data class CategoryDto(
    val id: Int,
    val name: String,
    val image: String
)

// Helper function to convert DTO (Network data) to Domain Model (Our clean app data)
fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category.name,
        // Clean up the image URL (Platzi sometimes adds brackets)
        imageUrl = images.firstOrNull()?.replace("[\"", "")?.replace("\"]", "") ?: ""
    )
}
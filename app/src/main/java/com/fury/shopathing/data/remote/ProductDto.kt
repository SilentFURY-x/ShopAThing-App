package com.fury.shopathing.data.remote

import com.fury.shopathing.domain.model.Product
import com.google.gson.annotations.SerializedName

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    // Platzi sends a LIST of strings
    val images: List<String>,
    // Platzi sends category as an Object
    val category: CategoryDto
)

data class CategoryDto(
    val id: Int,
    val name: String,
    val image: String
)

// Update the mapper function
fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category.name, // Extract name from Category Object
        // Take the first image.
        // NOTE: Sometimes Platzi URLs have extra quotes, so we clean them.
        imageUrl = images.firstOrNull()?.replace("[\"", "")?.replace("\"]", "") ?: ""
    )
}
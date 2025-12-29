package com.fury.shopathing.data.remote

data class ProductResponseDto(
    val products: List<ProductDto>, // The actual list is inside here
    val total: Int,
    val skip: Int,
    val limit: Int
)
package com.fury.shopathing.domain.repository

import com.fury.shopathing.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(id: Int): Product
}
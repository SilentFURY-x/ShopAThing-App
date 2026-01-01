package com.fury.shopathing.domain.repository

import com.fury.shopathing.domain.model.Product
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    // Update signature to accept optional filters
    fun getProducts(query: String? = null, category: String? = null): Flow<PagingData<Product>>

    suspend fun getCategories(): List<String> // New function
    suspend fun getProductById(id: Int): Product
}
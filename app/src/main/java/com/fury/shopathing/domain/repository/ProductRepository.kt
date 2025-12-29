package com.fury.shopathing.domain.repository

import com.fury.shopathing.domain.model.Product
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<PagingData<Product>>
    suspend fun getProductById(id: Int): Product
}
package com.fury.shopathing.domain.repository

import com.fury.shopathing.data.local.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllItems(): Flow<List<CartEntity>>
    suspend fun addToCart(item: CartEntity)
    suspend fun deleteItem(item: CartEntity)
    suspend fun updateQuantity(id: Int, quantity: Int)
}
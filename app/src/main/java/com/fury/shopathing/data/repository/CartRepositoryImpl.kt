package com.fury.shopathing.data.repository

import com.fury.shopathing.data.local.CartDao
import com.fury.shopathing.data.local.CartEntity
import com.fury.shopathing.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val dao: CartDao
) : CartRepository {

    override fun getAllItems(): Flow<List<CartEntity>> {
        return dao.getAllCartItems()
    }

    override suspend fun addToCart(item: CartEntity) {
        dao.addToCart(item)
    }

    override suspend fun deleteItem(item: CartEntity) {
        dao.deleteItem(item)
    }

    override suspend fun updateQuantity(id: Int, quantity: Int) {
        dao.updateQuantity(id, quantity)
    }
}
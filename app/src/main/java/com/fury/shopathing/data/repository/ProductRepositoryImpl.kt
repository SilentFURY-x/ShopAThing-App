package com.fury.shopathing.data.repository

import com.fury.shopathing.data.remote.ShopApi
import com.fury.shopathing.data.remote.toProduct
import com.fury.shopathing.domain.repository.ProductRepository
import javax.inject.Inject
import com.fury.shopathing.domain.model.Product

// @Inject tells Hilt how to create this class
class ProductRepositoryImpl @Inject constructor(
    private val api: ShopApi
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        // We fetch 20 items for now.
        // In Level 3, we will make this dynamic for infinite scrolling.
        return try {
            api.getProducts(offset = 0, limit = 20).map { it.toProduct() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Return empty list on error for now
        }
    }
}
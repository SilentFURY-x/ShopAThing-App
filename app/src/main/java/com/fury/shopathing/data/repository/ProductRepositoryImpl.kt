package com.fury.shopathing.data.repository

import com.fury.shopathing.data.remote.ShopApi
import com.fury.shopathing.data.remote.toProduct
import com.fury.shopathing.domain.repository.ProductRepository
import javax.inject.Inject
import com.fury.shopathing.domain.model.Product
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fury.shopathing.data.remote.ProductPagingSource
import kotlinx.coroutines.flow.Flow

// @Inject tells Hilt how to create this class
class ProductRepositoryImpl @Inject constructor(
    private val api: ShopApi
) : ProductRepository {

    override fun getProducts(query: String?, category: String?): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            // Pass the query/category to the factory
            pagingSourceFactory = { ProductPagingSource(api, query, category) }
        ).flow
    }

    override suspend fun getCategories(): List<String> {
        return try {
            val categories = api.getAllCategories()
            // Add "All" to the start of the list for the UI
            listOf("All") + categories
        } catch (e: Exception) {
            listOf("All") // Fallback
        }
    }

    override suspend fun getProductById(id: Int): Product {
        return api.getProductById(id).toProduct()
    }
}
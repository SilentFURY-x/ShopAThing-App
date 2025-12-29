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

    override fun getProducts(): Flow<PagingData<Product>> {
        // Pager is the class that connects the PagingSource to the UI
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Load 20 items at a time
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductPagingSource(api) }
        ).flow
    }

    override suspend fun getProductById(id: Int): Product {
        return api.getProductById(id).toProduct()
    }
}
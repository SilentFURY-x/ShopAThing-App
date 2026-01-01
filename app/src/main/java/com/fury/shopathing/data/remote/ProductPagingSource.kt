package com.fury.shopathing.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fury.shopathing.domain.model.Product

class ProductPagingSource(
    private val api: ShopApi,
    private val query: String? = null,    // Search query
    private val category: String? = null  // Selected category
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val position = params.key ?: 0
            val limit = params.loadSize

            // DECIDE WHICH ENDPOINT TO CALL
            val responseWrapper = when {
                // Case 1: Searching
                !query.isNullOrEmpty() -> api.searchProducts(query, position, limit)

                // Case 2: Filtering by Category
                !category.isNullOrEmpty() && category != "All" -> api.getProductsByCategory(category, position, limit)

                // Case 3: Default (All Products)
                else -> api.getProducts(position, limit)
            }

            val products = responseWrapper.products.map { it.toProduct() }

            // Logic for nextKey remains the same
            val nextKey = if (products.isEmpty()) null else position + limit

            LoadResult.Page(
                data = products,
                prevKey = if (position == 0) null else position - limit,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }
}
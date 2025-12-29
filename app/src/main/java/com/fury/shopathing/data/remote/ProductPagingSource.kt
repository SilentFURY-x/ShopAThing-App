package com.fury.shopathing.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fury.shopathing.domain.model.Product

class ProductPagingSource(
    private val api: ShopApi
) : PagingSource<Int, Product>() {

    // The logic to fetch a "page" of data
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            // 1. Get the current offset (default to 0 if null)
            val offset = params.key ?: 0

            // 2. Fetch data from API (limit comes from the Pager config)
            // Note: params.loadSize is usually 3 * pageSize for the first load
            val response = api.getProducts(offset = offset, limit = params.loadSize)

            // 3. Map DTO to Domain Model
            val products = response.map { it.toProduct() }

            // 4. Return the Page
            LoadResult.Page(
                data = products,
                // If offset is 0, we can't go back, so prevKey is null
                prevKey = if (offset == 0) null else offset - params.loadSize,
                // If empty list, we are at the end, so nextKey is null.
                // Else, next offset is current offset + number of items loaded
                nextKey = if (products.isEmpty()) null else offset + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    // This handles what happens if the list invalidates (like swipe-to-refresh)
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }
}
package com.fury.shopathing.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fury.shopathing.domain.model.Product
import android.util.Log // Add Logging

class ProductPagingSource(
    private val api: ShopApi
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val offset = params.key ?: 0
            val loadSize = params.loadSize

            Log.d("PagingDebug", "Requesting: Offset=$offset, Limit=$loadSize")

            // 1. Fetch from API
            val response = api.getProducts(offset = offset, limit = params.loadSize)

            Log.d("PagingDebug", "API Response Size: ${response.size}")

            // 2. Safe Mapping (The Fix)
            // Instead of .map {}, we use .mapNotNull {} to skip broken items
            val products = response.mapNotNull { dto ->
                try {
                    dto.toProduct() // Try to convert
                } catch (e: Exception) {
                    // If a specific item is broken (garbage data), skip it!
                    Log.e("PagingSource", "Skipping bad item: ${dto.id}")
                    null
                }
            }

            Log.d("PagingDebug", "Valid Products after filtering: ${products.size}")

            // 3. Return valid products only
            LoadResult.Page(
                data = products,
                prevKey = if (offset == 0) null else offset - loadSize,
                // Fix Logic: If we got fewer items than requested, we might be at the end,
                // BUT let's try to fetch more anyway unless it's strictly empty.
                nextKey = if (response.isEmpty()) null else offset + loadSize
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    // ... getRefreshKey stays the same ...
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }
}
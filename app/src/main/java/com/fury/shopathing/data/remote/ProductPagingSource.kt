package com.fury.shopathing.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fury.shopathing.domain.model.Product

class ProductPagingSource(
    private val api: ShopApi
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val position = params.key ?: 0

            // 1. Use 'skip' instead of 'offset'
            val responseWrapper = api.getProducts(skip = position, limit = params.loadSize)

            // 2. Extract the list from the wrapper
            val products = responseWrapper.products.map { it.toProduct() }

            // 3. Calculate next key
            val nextKey = if (products.isEmpty()) null else position + params.loadSize

            LoadResult.Page(
                data = products,
                prevKey = if (position == 0) null else position - params.loadSize,
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
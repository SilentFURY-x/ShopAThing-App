package com.fury.shopathing.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApi {

    @GET("products")
    suspend fun getProducts(
        @Query("skip") skip: Int, // DummyJSON uses 'skip'
        @Query("limit") limit: Int
    ): ProductResponseDto // Return the Wrapper

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDto
}
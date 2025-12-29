package com.fury.shopathing.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApi {

    @GET("products")
    suspend fun getProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<ProductDto>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDto
}
package com.fury.shopathing.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApi {

    // 1. Get All Products
    @GET("products")
    suspend fun getProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): ProductResponseDto

    // 2. Search Products
    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): ProductResponseDto

    // 3. Get Products by Category
    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): ProductResponseDto

    // 4. Get List of Categories (for the UI chips)
    @GET("products/categories")
    suspend fun getAllCategories(): List<String> // Returns ["smartphones", "laptops", ...]

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto
}
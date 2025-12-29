package com.fury.shopathing.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    // Get all cart items as a real-time stream (Flow)
    @Query("SELECT * FROM cart_table")
    fun getAllCartItems(): Flow<List<CartEntity>>

    // Insert or Update (if it exists, replace it)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(item: CartEntity)

    // Delete a specific item
    @Delete
    suspend fun deleteItem(item: CartEntity)

    // Clear cart (for Checkout)
    @Query("DELETE FROM cart_table")
    suspend fun clearCart()

    // Update quantity specifically (Level 3 Requirement)
    @Query("UPDATE cart_table SET quantity = :quantity WHERE productId = :id")
    suspend fun updateQuantity(id: Int, quantity: Int)

    @Query("SELECT * FROM cart_table WHERE productId = :id")
    suspend fun getCartItemById(id: Int): CartEntity?
}
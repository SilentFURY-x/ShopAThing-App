package com.fury.shopathing.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class CartEntity(
    @PrimaryKey
    val productId: Int, // Use product ID as primary key so we don't have duplicates
    val title: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int = 1
)
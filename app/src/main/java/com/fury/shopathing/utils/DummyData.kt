package com.fury.shopathing.utils

import com.fury.shopathing.domain.model.Product

object DummyData {
    val productList = listOf(
        Product(
            id = 1,
            title = "Wireless Headphones",
            price = 99.99,
            description = "High quality noise cancelling headphones.",
            category = "Electronics",
            imageUrl = "https://picsum.photos/600/600?random=1" // reliable random image
        ),
        Product(
            id = 2,
            title = "Mens Casual T-Shirt",
            price = 22.30,
            description = "Slim fit, comfortable cotton t-shirt.",
            category = "Clothing",
            imageUrl = "https://picsum.photos/600/600?random=2"
        ),
        Product(
            id = 3,
            title = "Mens Cotton Jacket",
            price = 55.99,
            description = "Great outerwear for all seasons.",
            category = "Clothing",
            imageUrl = "https://picsum.photos/600/600?random=3"
        ),
        Product(
            id = 4,
            title = "Gaming Monitor 24\"",
            price = 149.99,
            description = "144Hz refresh rate for smooth gaming.",
            category = "Electronics",
            imageUrl = "https://picsum.photos/600/600?random=4"
        )
    )
}
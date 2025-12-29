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
            imageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
        ),
        Product(
            id = 2,
            title = "Mens Casual T-Shirt",
            price = 22.30,
            description = "Slim fit, comfortable cotton t-shirt.",
            category = "Clothing",
            imageUrl = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"
        ),
        Product(
            id = 3,
            title = "Mens Cotton Jacket",
            price = 55.99,
            description = "Great outerwear for all seasons.",
            category = "Clothing",
            imageUrl = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"
        ),
        Product(
            id = 4,
            title = "Gaming Monitor 24\"",
            price = 149.99,
            description = "144Hz refresh rate for smooth gaming.",
            category = "Electronics",
            imageUrl = "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg"
        )
    )
}
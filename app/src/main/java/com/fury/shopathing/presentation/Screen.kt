package com.fury.shopathing.presentation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Detail : Screen("detail_screen/{productId}") {
        // Helper function to build the route with the actual ID
        fun createRoute(productId: Int) = "detail_screen/$productId"
    }
    // We will add Login and Cart here later
}
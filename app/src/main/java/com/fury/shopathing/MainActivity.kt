package com.fury.shopathing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fury.shopathing.presentation.theme.ShopathingTheme
import com.fury.shopathing.presentation.screens.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fury.shopathing.presentation.Screen
import com.fury.shopathing.presentation.screens.detail.DetailScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopathingTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {

                    // 1. The Home Screen
                    composable(route = Screen.Home.route) {
                        HomeScreen(
                            navController = navController // Pass controller to Home
                        )
                    }

                    // 2. The Detail Screen (expects an ID)
                    composable(
                        route = Screen.Detail.route,
                        arguments = listOf(navArgument("productId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        // Extract the ID from the URL
                        val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                        DetailScreen(productId = productId)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShopathingTheme {
        Greeting("Android")
    }
}
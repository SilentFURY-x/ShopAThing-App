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
import com.fury.shopathing.presentation.screens.auth.LoginScreen
import com.fury.shopathing.presentation.screens.auth.SignupScreen
import com.fury.shopathing.presentation.screens.auth.SplashScreen
import com.fury.shopathing.presentation.screens.cart.CartScreen
import com.fury.shopathing.presentation.screens.detail.DetailScreen
import com.fury.shopathing.presentation.screens.profile.ProfileScreen
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.fury.shopathing.presentation.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // 1. Get the MainViewModel
            val mainViewModel: MainViewModel = hiltViewModel()

            // 2. Observe the Theme State
            val isDarkTheme by mainViewModel.isDarkMode.collectAsState()

            // 3. Pass it to your Theme
            ShopathingTheme(darkTheme = isDarkTheme) {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Splash.route
                ) {

                    composable(route = Screen.Profile.route) {
                        ProfileScreen(navController = navController)
                    }

                    composable(Screen.Cart.route) { CartScreen(navController) }

                    // 0. Splash Screen
                    composable(route = Screen.Splash.route) {
                        SplashScreen(navController = navController)
                    }
                    // 1. Login Screen
                    composable(route = Screen.Login.route) {
                        LoginScreen(navController = navController)
                    }

                    // 2. Signup Screen
                    composable(route = Screen.Signup.route) {
                        SignupScreen(navController = navController)
                    }

                    // 3. Home Screen
                    composable(route = Screen.Home.route) {
                        HomeScreen(navController = navController)
                    }

                    // 4. Detail Screen
                    composable(
                        route = Screen.Detail.route,
                        arguments = listOf(navArgument("productId") { type = NavType.IntType })
                    )

                    { backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                        DetailScreen(navController = navController) // You might need to add logic here later if needed
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
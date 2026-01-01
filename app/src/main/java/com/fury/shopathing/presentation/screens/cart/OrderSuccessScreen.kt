package com.fury.shopathing.presentation.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.fury.shopathing.R // Make sure to import your R file
import com.fury.shopathing.presentation.Screen

@Composable
fun OrderSuccessScreen(
    navController: NavController
) {
    // 1. Setup Lottie Animation
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.order_success))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1 // Play only once
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 2. The Animation
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 3. Success Text
        Text(
            text = "Order Placed Successfully!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Thank you for shopping with us.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(64.dp))

        // 4. Back to Home Button
        Button(
            onClick = {
                // Pop everything up to Home so the user can't go "Back" to the Success screen
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Continue Shopping")
        }
    }
}
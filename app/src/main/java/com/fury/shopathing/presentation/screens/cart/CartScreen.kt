package com.fury.shopathing.presentation.screens.cart

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fury.shopathing.presentation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val total by viewModel.totalPrice.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping Cart") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                Button(
                    onClick = {
                        viewModel.checkout()
                        Toast.makeText(context, "Enjoy Your Goodies!", Toast.LENGTH_LONG).show()
                        navController.navigate(Screen.OrderSuccess.route)
                    },
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Text("Checkout ($${String.format("%.2f", total)})")
                }
            }
        }
    ) { paddingValues ->
        if (cartItems.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("Your Cart is Empty", style = MaterialTheme.typography.titleLarge)
            }
        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(cartItems) { item ->
                    CartItem(
                        item = item,
                        onIncrement = { viewModel.increment(item) },
                        onDecrement = { viewModel.decrement(item) },
                        onDelete = { viewModel.delete(item) }
                    )
                }
            }
        }
    }
}
package com.fury.shopathing.presentation.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fury.shopathing.presentation.components.ProductCard
import com.fury.shopathing.utils.DummyData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ShopAThing") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        // This is the Grid View
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 Columns like a real shop
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(DummyData.productList) { product ->
                ProductCard(
                    product = product,
                    onClick = { /* TODO: Navigate to Details later */ }
                )
            }
        }
    }
}
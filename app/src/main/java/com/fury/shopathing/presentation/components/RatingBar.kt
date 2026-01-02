package com.fury.shopathing.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        val stars = (rating.roundToInt()).coerceIn(1, 5)
        repeat(5) { index ->
            Icon(
                imageVector = if (index < stars) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = null,
                tint = if (index < stars) Color(0xFFFFC107) else MaterialTheme.colorScheme.outline, // Gold color for stars
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
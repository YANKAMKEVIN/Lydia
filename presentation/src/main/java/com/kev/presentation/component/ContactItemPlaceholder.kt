package com.kev.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kev.presentation.loading.shimmerBrush

@Composable
fun ContactItemPlaceholder() {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(
                shimmerBrush(true),
                shape = RoundedCornerShape(12.dp)
            )
    ) { }
}
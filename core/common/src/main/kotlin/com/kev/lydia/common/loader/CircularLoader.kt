package com.kev.lydia.common.loader

import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularLoader(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        color = Color.Blue,
        trackColor = Color.White,
        strokeWidth = 5.dp,
        modifier = modifier.height(96.dp)
    )
}
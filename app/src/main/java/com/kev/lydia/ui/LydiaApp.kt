package com.kev.lydia.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kev.presentation.HomeScreen

@Composable
fun LydiaApp() {
    Scaffold(
        topBar = {

        },
        content = {
            HomeScreen(Modifier.padding(it))
        }
    )
}

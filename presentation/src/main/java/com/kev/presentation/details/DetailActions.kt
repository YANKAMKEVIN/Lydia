package com.kev.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailActions(
    phone: String,
    email: String,
    onCall: (String) -> Unit,
    onEmail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(onClick = { onCall(phone) }) {
            Icon(Icons.Default.Call, contentDescription = "Appeler")
        }
        Spacer(Modifier.height(12.dp))
        FloatingActionButton(onClick = { onEmail(email) }) {
            Icon(Icons.Default.Email, contentDescription = "Envoyer un e-mail")
        }
    }
}

package com.kev.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kev.domain.model.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    contact: Contact,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Détails de ${contact.fullName}") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = contact.avatarUrl,
                contentDescription = "Avatar de ${contact.fullName}",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(contact.fullName, style = MaterialTheme.typography.headlineSmall)
            Text("Email : ${contact.email}", style = MaterialTheme.typography.bodyMedium)
            Text("Téléphone : ${contact.phone}", style = MaterialTheme.typography.bodyMedium)
            Text("Mobile : ${contact.phone}", style = MaterialTheme.typography.bodyMedium)
            //  Text("Genre : ${contact}", style = MaterialTheme.typography.bodyMedium)
            //Text("Âge : ${contact.}", style = MaterialTheme.typography.bodyMedium)
            // Text("Date de naissance : ${contact.dobDate}", style = MaterialTheme.typography.bodyMedium)
            Text(
                "Localisation : ${contact.city}, ${contact.country}",
                style = MaterialTheme.typography.bodyMedium
            )
            // Text("ID (${contact.idName}) : ${contact.idValue}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

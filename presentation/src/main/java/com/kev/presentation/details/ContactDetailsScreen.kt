package com.kev.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kev.domain.model.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    contact: Contact,
    onNavigateBack: () -> Unit
) {
    contact.let { c ->
        // Pour le parallax header
        val scroll = rememberScrollState(0)
        Box {
            // 1) Header image avec overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .graphicsLayer {
                        // Effet parallax en fonction du scroll
                        translationY = scroll.value * 0.5f
                    }
            ) {
                AsyncImage(
                    model = c.avatarUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Overlay gradient
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.surfaceVariant
                                ),
                                startY = 100f
                            )
                        )
                )
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(36.dp)
                        .background(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                            CircleShape
                        )
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                }
            }

            // 2) Contenu scrollable
            Column(
                modifier = Modifier
                    .verticalScroll(scroll)
                    .padding(top = 260.dp)  // remonte le contenu sur le header
                    .fillMaxSize()
            ) {
                // Card principale
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(c.fullName, style = MaterialTheme.typography.headlineSmall)
                        Spacer(Modifier.height(8.dp))
                        InfoRow(icon = Icons.Default.Email, text = c.email)
                        Spacer(Modifier.height(4.dp))
                        InfoRow(icon = Icons.Default.Phone, text = c.phone)
                        Spacer(Modifier.height(4.dp))
                        InfoRow(icon = Icons.Default.Home, text = "${c.city}, ${c.country}")
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Section “Infos complémentaires”
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Informations supplémentaires",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(8.dp))
                       /* InfoRow(icon = Icons.Default.Face, text = "Age : ${c.dobAge}")
                        Spacer(Modifier.height(4.dp))
                        InfoRow(
                            icon = Icons.Default.CalendarToday,
                            text = "Né(e) le : ${c.dobDate.take(10)}"
                        )
                        Spacer(Modifier.height(4.dp))
                        InfoRow(
                            icon = Icons.Default.Badge,
                            text = "${c.idName} : ${c.idValue ?: "N/A"}"
                        )
                        Spacer(Modifier.height(4.dp))
                        InfoRow(
                            icon = Icons.Default.Person,
                            text = "Genre : ${c.gender.capitalize()}"
                        )*/
                    }
                }

                Spacer(Modifier.height(80.dp)) // pour le FAB
            }

            // 3) FABs pour action rapide
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                FloatingActionButton(onClick = { /* lancer un appel */ }) {
                    Icon(Icons.Default.Call, contentDescription = "Appeler")
                }
                Spacer(Modifier.height(12.dp))
                FloatingActionButton(onClick = { /* ouvrir mail client */ }) {
                    Icon(Icons.Default.Email, contentDescription = "Envoyer un e-mail")
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}
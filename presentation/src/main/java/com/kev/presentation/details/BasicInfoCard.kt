package com.kev.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kev.domain.model.Contact

@Composable
fun BasicInfoCard(contact: Contact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(contact.fullName, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            InfoRow(Icons.Default.Email, contact.email)
            InfoRow(Icons.Default.Phone, contact.phone)
            InfoRow(Icons.Default.Home, "${contact.city}, ${contact.country}")
        }
    }
}

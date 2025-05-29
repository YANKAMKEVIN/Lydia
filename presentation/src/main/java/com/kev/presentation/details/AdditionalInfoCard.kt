package com.kev.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kev.domain.model.Contact

@Composable
fun AdditionalInfoCard(contact: Contact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Informations supplémentaires", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            InfoRow(Icons.Default.Face, "Âge : ${contact.age}")
            InfoRow(Icons.Default.DateRange, "Inscrit le : ${contact.registeredDate.take(10)}")
            InfoRow(Icons.Default.AccountCircle, "Utilisateur : ${contact.username}")
            InfoRow(
                Icons.Default.Person,
                "Genre : ${contact.gender.replaceFirstChar { it.uppercaseChar() }}"
            )
        }
    }
}

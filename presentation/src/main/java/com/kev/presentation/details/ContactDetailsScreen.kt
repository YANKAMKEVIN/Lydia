package com.kev.presentation.details

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kev.domain.model.Contact

@Composable
fun ContactDetailScreen(
    contact: Contact,
    onNavigateBack: () -> Unit
) {
    val scroll = rememberScrollState(0)
    val context = LocalContext.current

    Box {
        DetailHeader(
            avatarUrl = contact.avatarUrl,
            scrollState = scroll,
            onBack = onNavigateBack
        )

        Column(
            modifier = Modifier
                .verticalScroll(scroll)
                .padding(top = 260.dp)
                .fillMaxSize()
        ) {
            BasicInfoCard(contact)
            Spacer(Modifier.height(16.dp))
            AdditionalInfoCard(contact)
            Spacer(Modifier.height(80.dp))
        }

        DetailActions(
            phone = contact.phone,
            email = contact.email,
            onCall = { phoneNumber ->
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = "tel:$phoneNumber".toUri()
                }
                context.startActivity(intent)
            },
            onEmail = { emailAddress ->
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = "mailto:$emailAddress".toUri()
                    putExtra(Intent.EXTRA_SUBJECT, "Bonjour ${contact.fullName}")
                    putExtra(Intent.EXTRA_TEXT, "Je vous contacte au sujet de...")
                }
                context.startActivity(intent)
            }
        )
    }
}
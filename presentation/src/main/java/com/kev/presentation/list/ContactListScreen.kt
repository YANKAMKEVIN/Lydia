package com.kev.presentation.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kev.domain.model.Contact

@Composable
fun ContactListRoute(
    viewModel: ContactViewModel = hiltViewModel(),
    onNavigateToDetail: (contact: Contact) -> Unit,
) {
    val contactsPagingItems: LazyPagingItems<Contact> =
        viewModel.contactsState.collectAsLazyPagingItems()

    ContactListScreen(contactsPagingItems, onNavigateToDetail)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    contactsPagingItems: LazyPagingItems<Contact>,
    onNavigateToDetail: (contact: Contact) -> Unit
) {
    var showSearch by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }
    val filter: (Contact) -> Boolean = remember(query) {
        { contact -> query.isBlank() || contact.fullName.contains(query, ignoreCase = true) }
    }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text("Mes Contacts") },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = { showSearch = !showSearch }) {
                        Icon(Icons.Default.Search, contentDescription = "Rechercher")
                    }
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        Column(Modifier.padding(padding)) {
            AnimatedVisibility(visible = showSearch) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("Rechercher un contact") },
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = {
                            query = ""
                            showSearch = false
                        }) {
                            Icon(Icons.Default.Close, contentDescription = "Fermer recherche")
                        }
                    }
                )
            }
            ContactListBody(
                contactsPagingItems = contactsPagingItems,
                onNavigateToDetail = onNavigateToDetail,
                filter = filter,
                query = query
            )
        }
    }
}
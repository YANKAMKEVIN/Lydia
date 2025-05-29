package com.kev.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text("Mes Contacts") },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = { /* TODO: ouvrir recherche */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Rechercher")
                    }
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        Column(Modifier.padding(padding)) {
            /* SearchBar(
                 query = "",
                 onQueryChange = { /* filtrer ton LazyPagingItems */ },
                 onSearch = { /* idem */ },
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(horizontal = 16.dp, vertical = 8.dp),
                 inputField = TODO(),
                 expanded = TODO(),
                 onExpandedChange = TODO(),
                 shape = TODO(),
                 colors = TODO(),
                 tonalElevation = TODO(),
                 shadowElevation = TODO(),
                 windowInsets = TODO(),
                 content = TODO()
             )*/
            ContactListBody(
                contactsPagingItems = contactsPagingItems,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}
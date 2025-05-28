package com.kev.presentation.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kev.domain.model.Contact
import com.kev.presentation.component.ContactItem
import com.kev.presentation.component.ErrorMessage
import com.kev.presentation.component.LoadingNextPageItem
import com.kev.presentation.component.PageLoader

@Composable
fun ContactListRoute(
    viewModel: ContactViewModel = hiltViewModel(),
    onNavigateToDetail: (contact: Contact) -> Unit,
) {
    val contactsPagingItems: LazyPagingItems<Contact> =
        viewModel.contactsState.collectAsLazyPagingItems()

    ContactListScreen(contactsPagingItems, onNavigateToDetail)
}

@Composable
fun ContactListScreen(
    contactsPagingItems: LazyPagingItems<Contact>,
    onNavigateToDetail: (contact: Contact) -> Unit
) {

    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(contactsPagingItems.itemCount) { index ->
            contactsPagingItems[index]?.let { contact ->
                ContactItem(
                    contact = contact,
                    onClick = { onNavigateToDetail(contact) }
                )
            }
        }
        contactsPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = contactsPagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = contactsPagingItems.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}
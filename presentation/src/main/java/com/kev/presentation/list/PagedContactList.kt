package com.kev.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.kev.domain.model.Contact
import com.kev.presentation.component.ContactItem
import com.kev.presentation.component.ContactItemPlaceholder
import com.kev.presentation.component.ErrorMessage
import com.kev.presentation.component.LoadingNextPageItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagedContactList(
    contactsPagingItems: LazyPagingItems<Contact>,
    onNavigateToDetail: (Contact) -> Unit,
) {
    val isRefreshing = contactsPagingItems.loadState.refresh is LoadState.Loading
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { contactsPagingItems.refresh() },
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(contactsPagingItems.itemCount) { index ->
                contactsPagingItems[index]?.let { contact ->
                    ContactItem(
                        contact = contact,
                        onClick = { onNavigateToDetail(contact) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
            contactsPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        repeat(5) {
                            item { ContactItemPlaceholder() }
                        }
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
}
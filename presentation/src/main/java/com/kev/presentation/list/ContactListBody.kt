package com.kev.presentation.list

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.kev.domain.model.Contact

@Composable
fun ContactListBody(
    contactsPagingItems: LazyPagingItems<Contact>,
    onNavigateToDetail: (Contact) -> Unit,
    filter: (Contact) -> Boolean,
    query: String
) {
    if (query.isBlank()) {
        PagedContactList(contactsPagingItems, onNavigateToDetail)
    } else {
        val filtered = contactsPagingItems.itemSnapshotList.items.filter(filter)
        FilteredContactList(
            filteredContacts = filtered,
            onNavigateToDetail = onNavigateToDetail
        )
    }

}

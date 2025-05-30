package com.kev.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.kev.domain.model.Contact
import com.kev.presentation.component.ContactItem

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
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = filtered,
                key = { it.id }
            ) { contact ->
                ContactItem(
                    contact = contact,
                    onClick = { onNavigateToDetail(contact) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }

}

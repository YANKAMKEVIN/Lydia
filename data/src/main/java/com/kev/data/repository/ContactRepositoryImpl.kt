package com.kev.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kev.data.datasource.ContactPagingSource
import com.kev.data.datasource.remote.ContactDataSource
import com.kev.domain.model.Contact
import com.kev.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of the [ContactRepository] interface.
 * This class is responsible for processing the data retrieved from the [ContactDataSource]
 * and transforming it into the domain model before passing it to the use cases.
 */
class ContactRepositoryImpl @Inject constructor(
    private val contactDataSource: ContactDataSource
) : ContactRepository {

    /**
     * Fetches a list of contact from the data source and maps the result into the domain model.
     * The result is emitted as a [Flow] containing a list of [Contact].
     *
     * @return A [Flow] emitting a list of [Contact] containing the list of contacts.
     */
    override suspend fun fetchContacts(): Flow<PagingData<Contact>> =
        Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { ContactPagingSource(contactDataSource) },
        ).flow
}
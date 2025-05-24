package com.kev.data.repository

import com.kev.data.datasource.ContactDataSource
import com.kev.data.mapper.ContactMapper.toDomain
import com.kev.domain.model.Contact
import com.kev.domain.repository.ContactRepository
import com.kev.lydia.common.network.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    override suspend fun fetchContacts(page: Int): Flow<List<Contact>> = flow {
        when (val response = contactDataSource.fetchContacts(page)) {
            is NetworkResponse.Success -> {
                val realEstateListings = response.data.results.map { it.toDomain() }
                emit(realEstateListings)
            }

            is NetworkResponse.Failure -> {
                throw Exception("Error ${response.error.message}: ${response.error.code}")
            }
        }
    }

}
package com.kev.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kev.data.datasource.remote.ContactDataSource
import com.kev.data.mapper.ContactMapper.toDomain
import com.kev.domain.model.Contact
import com.kev.lydia.common.network.NetworkResponse

class ContactPagingSource(
    private val remoteDataSource: ContactDataSource,
) : PagingSource<Int, Contact>() {
    override fun getRefreshKey(state: PagingState<Int, Contact>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Contact> {
        val currentPage = params.key ?: 1
        return when (val response = remoteDataSource.fetchContacts(page = currentPage)) {
            is NetworkResponse.Success -> {
                val contact = response.data.results.map { it.toDomain() }
                LoadResult.Page(
                    data = contact,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (contact.isEmpty()) null else currentPage + 1
                )
            }

            is NetworkResponse.Failure -> {
                LoadResult.Error(Exception(response.error.message))
            }
        }
    }
}
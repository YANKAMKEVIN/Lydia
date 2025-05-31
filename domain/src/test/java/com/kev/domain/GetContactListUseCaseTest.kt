package com.kev.domain

import androidx.paging.PagingData
import com.kev.domain.model.Contact
import com.kev.domain.repository.ContactRepository
import com.kev.domain.usecase.GetContactsListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertSame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetContactListUseCaseTest {
    private val repository = mockk<ContactRepository>()
    private lateinit var useCase: GetContactsListUseCase

    @Before
    fun setup() {
        useCase = GetContactsListUseCase(repository::fetchContacts)
    }

    @Test
    fun `invoke should return flow from repository`() = runTest {
        val dummyFlow: Flow<PagingData<Contact>> = flowOf(PagingData.from(emptyList()))
        coEvery { repository.fetchContacts() } returns dummyFlow

        val resultFlow = useCase.invoke()

        assertSame(dummyFlow, resultFlow)
    }
}
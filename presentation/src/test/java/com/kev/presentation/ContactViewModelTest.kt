package com.kev.presentation

import androidx.paging.PagingData
import com.kev.domain.model.Contact
import com.kev.domain.usecase.GetContactsListUseCase
import com.kev.presentation.list.ContactViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ContactViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val useCase = mockk<GetContactsListUseCase>(relaxed = true)
    private lateinit var viewModel: ContactViewModel

    @Test
    fun `init triggers useCase and emits non-empty PagingData`() = runTest {
        val dummyContact = Contact(
            id = "1",
            fullName = "Alice Dupont",
            username = "alice123",
            gender = "female",
            email = "alice.dupont@example.com",
            phone = "0123456789",
            avatarUrl = "https://example.com/avatar1.jpg",
            city = "Paris",
            country = "France",
            age = 30,
            registeredDate = "2020-01-15"
        )
        val dummyPaging = PagingData.from(listOf(dummyContact))

        coEvery { useCase.invoke() } returns flowOf(dummyPaging)

        viewModel = ContactViewModel(useCase)

        testDispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 1) { useCase.invoke() }

        val resultPaging = viewModel.contactsState.value

        assertNotNull(resultPaging)
        assertFalse(resultPaging == PagingData.empty<Contact>())
    }
}

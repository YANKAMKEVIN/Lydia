package com.kev.data

import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.kev.data.datasource.local.ContactDao
import com.kev.data.datasource.local.ContactDatabase
import com.kev.data.datasource.local.ContactEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ContactDaoTest {

    private lateinit var database: ContactDatabase
    private lateinit var contactDao: ContactDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        contactDao = database.contactDao
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testUpsertAndRetrieveById() = runTest {
        val entity = ContactEntity(
            id = "123",
            fullName = "Alice Dupont",
            email = "alice@example.com",
            phone = "0123456789",
            avatarUrl = "https://example.com/a.jpg",
            city = "Paris",
            country = "France",
            age = 25,
            registeredDate = "2022-01-01",
            username = "alice",
            gender = "female"
        )
        contactDao.upsertAll(listOf(entity))

        val page = contactDao.pagingSource().load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
        ) as PagingSource.LoadResult.Page

        assertEquals(1, page.data.size)
        val retrieved = page.data[0]
        assertEquals("123", retrieved.id)
        assertEquals("Alice Dupont", retrieved.fullName)
        assertEquals("alice@example.com", retrieved.email)
    }

    @Test
    fun testClearAll() = runTest {
        val entities = listOf(
            ContactEntity(
                id = "1",
                fullName = "User1",
                email = "u1@example.com",
                phone = "000",
                avatarUrl = "url",
                city = "City",
                country = "Country",
                age = 25,
                registeredDate = "2022-01-01",
                username = "user1",
                gender = "male"
            ),

            ContactEntity(
                id = "2",
                fullName = "User2",
                email = "u2@example.com",
                phone = "000",
                avatarUrl = "url",
                city = "City",
                country = "Country",
                age = 25,
                registeredDate = "2022-01-01",
                username = "user2",
                gender = "female"
            ),
        )
        contactDao.upsertAll(entities)

        var page = contactDao.pagingSource().load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
        ) as PagingSource.LoadResult.Page
        assertEquals(2, page.data.size)

        contactDao.clearAll()

        page = contactDao.pagingSource().load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
        ) as PagingSource.LoadResult.Page
        assertEquals(0, page.data.size)
    }
}

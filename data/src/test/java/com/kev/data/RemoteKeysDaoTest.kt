package com.kev.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.kev.data.datasource.local.ContactDatabase
import com.kev.data.datasource.local.RemoteKeys
import com.kev.data.datasource.local.RemoteKeysDao
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RemoteKeysDaoTest {

    private lateinit var database: ContactDatabase
    private lateinit var keysDao: RemoteKeysDao

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        keysDao = database.remoteKeysDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertAndGetRemoteKeys() = runTest {
        val key = RemoteKeys(repoId = "abc", prevKey = 1, nextKey = 2)
        keysDao.insertAll(listOf(key))

        val retrieved = keysDao.remoteKeysById("abc")
        assertNotNull(retrieved)
        assertEquals(1, retrieved!!.prevKey)
        assertEquals(2, retrieved.nextKey)
    }

    @Test
    fun testClearRemoteKeys() = runTest {
        val key = RemoteKeys(repoId = "xyz", prevKey = 0, nextKey = 1)
        keysDao.insertAll(listOf(key))

        var retrieved = keysDao.remoteKeysById("xyz")
        assertNotNull(retrieved)

        keysDao.clearRemoteKeys()

        retrieved = keysDao.remoteKeysById("xyz")
        assertNull(retrieved)
    }
}

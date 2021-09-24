package com.android.skeleton.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.android.skeleton.data.feature.AppDatabase
import com.android.skeleton.domain.Item
import com.android.skeleton.utility.getValue
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.rules.ExpectedException
import java.io.IOException

/**
 * Tests [RepositoryItem]. The tests are only meant to illustrate how to perform a component test.
 * Uses a database that the test class itself creates.
 */
class RepositoryItemTest {

    private lateinit var database: AppDatabase
    private lateinit var repository: RepositoryItem

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var exception: ExpectedException = ExpectedException.none()

    @Before
    fun pre() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        repository = RepositoryItem(database)
    }

    @After
    @Throws(IOException::class)
    fun post() {
        database.close()
    }

    /**
     * Verifies that a new item is generated with the attributes specified.
     */
    @Test
    fun new() = runBlocking {
        val item = Item("name", "description", 0)
        repository.save(item)
        val itemRead = getValue(repository.loadAll()).first()
        Assert.assertEquals(item, itemRead)
    }

}
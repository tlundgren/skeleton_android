package com.android.skeleton.domain

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

/**
 * Tests [Item]. The tests are only meant to illustrate how to perform a basic unit test.
 */
class ItemTest {
    @get:Rule
    var exception: ExpectedException = ExpectedException.none()

    /**
     * Verifies the identity function of [Item]s.
     */
    @Test
    fun identity() {
        val oneItem = Item("name", "description", 0)
        val sameItem = Item("name", "description2", 0)
        val otherItem = Item("name2", "description", 0)

        Assert.assertEquals(oneItem, sameItem)
        Assert.assertEquals(oneItem.hashCode(), sameItem.hashCode())
        Assert.assertNotEquals(oneItem, otherItem)
        Assert.assertNotEquals(oneItem.hashCode(), otherItem.hashCode())
    }
}
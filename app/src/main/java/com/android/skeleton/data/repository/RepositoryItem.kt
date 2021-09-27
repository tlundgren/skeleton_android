package com.android.skeleton.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.skeleton.data.feature.DaoItem
import com.android.skeleton.data.feature.DbItem
import com.android.skeleton.domain.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Write, delete, read, [Item]s.
 */
class RepositoryItem @Inject constructor(private val daoItem: DaoItem) {
    private val defaultItem = Item("Item X", "Lorem ipsum...", 0)

    fun size(): LiveData<Int> {
        return daoItem.getCountLive()
    }

    fun loadAll(): LiveData<List<Item>> {
        return Transformations.map(daoItem.getAllLive()) {
            it.asDomainObject()
        }
    }

    suspend fun delete(item: Item) {
        withContext(Dispatchers.IO) {
            daoItem.delete(item.asDatabaseObject())
        }
    }

    suspend fun getItemAt(position: Int): Item {
        Timber.d("getItemAt $position")
        return withContext(Dispatchers.IO) {
            daoItem.getItemByPosition(position).asDomainObject()
        }
    }

    suspend fun save(item: Item) {
        Timber.d("save $item")
        withContext(Dispatchers.IO) {
            daoItem.insert(item.asDatabaseObject())
        }
    }

    suspend fun save(item: List<Item>) {
        Timber.d("save item list")
        withContext(Dispatchers.IO) {
            daoItem.insertN(item.asDatabaseObject())
        }
    }

    suspend fun new(): Item {
        return withContext(Dispatchers.IO) {
            val lastPosition = daoItem.getLastPosition()?: -1
            Timber.d("new At $lastPosition +1")
            Item(
                defaultItem.name,
                defaultItem.description,
                lastPosition+1
            )
        }
    }
}

fun Item.asDatabaseObject(): DbItem {
    return DbItem(name, description, position)
}

fun List<Item>.asDatabaseObject(): List<DbItem> {
    return map {
        it.asDatabaseObject()
    }
}

fun DbItem.asDomainObject(): Item {
    return Item(name, description, position)
}

fun List<DbItem>.asDomainObject(): List<Item> {
    return map {
        it.asDomainObject()
    }
}

package com.android.skeleton.data.feature

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.skeleton.domain.Item

/**
 * Data access object for [DbItem]s - ultimately, [Item]s.
 */
@Dao
interface DaoItem {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertN(item: List<DbItem>)

    @Delete
    suspend fun delete(item: DbItem)

    @Query("select * from item order by position asc")
    fun getAllLive(): LiveData<List<DbItem>>

    @Query("select * from item where position = :position limit 1")
    suspend fun getItemByPosition(position: Int): DbItem

    @Query("select max(position) from item")
    suspend fun getLastPosition(): Int?

    @Query("select count(name) as size from item")
    fun getCountLive(): LiveData<Int>
}
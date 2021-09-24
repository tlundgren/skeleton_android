package com.android.skeleton.data.feature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.skeleton.domain.Item

/**
 * Class to persist [DbItem]s - ultimately, [Item]s.
 */
@Entity(tableName = "item")
data class DbItem (
    @PrimaryKey val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "position") val position: Int
)
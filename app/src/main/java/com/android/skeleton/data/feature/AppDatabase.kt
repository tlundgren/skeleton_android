package com.android.skeleton.data.feature

import android.content.Context
import androidx.room.*

/**
 * Stores [DbItem]s.
 */
@Database(entities = [DbItem::class], version = 13)
abstract class AppDatabase : RoomDatabase() {
    abstract val daoItem: DaoItem

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    ).also {
                        instance = it
                    }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "app.db"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }

}

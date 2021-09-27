package com.android.skeleton.data.feature

import android.content.Context
import androidx.room.*

/**
 * Stores [DbItem]s.
 */
@Database(entities = [DbItem::class], version = 13)
abstract class DatabaseApp : RoomDatabase() {
    abstract val daoItem: DaoItem

    companion object {
        @Volatile
        private var instance: DatabaseApp? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    ).also {
                        instance = it
                    }
            }

        private fun buildDatabase(context: Context): DatabaseApp {
            return Room.databaseBuilder(
                context,
                DatabaseApp::class.java,
                "app.db"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }

}

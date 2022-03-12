package com.hfad.speednet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Infos::class], version = 1)
abstract class InfosRoomDatabase : RoomDatabase() {
    abstract fun getInfosDao(): InfosDao?

    companion object {
        private var INSTANCE: InfosRoomDatabase? = null
        fun getInMemoryDatabase(context: Context): InfosRoomDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.inMemoryDatabaseBuilder(
                    context
                        .applicationContext,
                    InfosRoomDatabase::class.java
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
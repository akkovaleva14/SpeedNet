package com.hfad.speednet

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Infos::class], version = 1)
abstract class InfosRoomDatabase : RoomDatabase() {
    abstract val infosDao: InfosDao?

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


/*
    // Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

   abstract fun wordDao(): WordDao

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "word_database"
                    ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
   }
}

Let's walk through the code:

The database class for Room must be abstract and extend RoomDatabase.
You annotate the class to be a Room database with @Database and use the annotation parameters
to declare the entities that belong in the database and set the version number. Each entity
corresponds to a table that will be created in the database. Database migrations are beyond
the scope of this codelab, so exportSchema has been set to false here, in order to avoid a
build warning. In a real app, consider setting a directory for Room to use to export the
 schema so you can check the current schema into your version control system.
The database exposes DAOs through an abstract "getter" method for each @Dao.
You defined a singleton, WordRoomDatabase, to prevent having multiple instances of the database
opened at the same time.
getDatabase returns the singleton. It'll create the database the first time it's accessed, using
 Room's database builder to create a RoomDatabase object in the application context
 from the WordRoomDatabase class and names it "word_database".
 */

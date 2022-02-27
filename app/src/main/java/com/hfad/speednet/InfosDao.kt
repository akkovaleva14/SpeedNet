package com.hfad.speednet

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface InfosDao {

    @Insert
    fun insert(info: Infos?)
}

/*  @Insert
  fun insertAll(vararg info: Infos?)

  @Delete
  fun delete(info: Infos?)

  @get:Query("SELECT*FROM infos")
  val allInfos: List<Any?>?


@Query("SELECT * FROM infos ORDER BY info ASC")
  fun getAlphabetizedWords(): List<Infos>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(word: Infos)

  @Query("DELETE FROM infos")
  suspend fun deleteAll() */
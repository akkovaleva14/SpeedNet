package com.hfad.speednet

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InfosDao {

    @Insert
    fun insert(info: Infos?)

    @Query("SELECT*FROM infos")
    fun getAllInfos(): List<Infos?>?

    @Delete
    fun delete(info: Infos?)
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
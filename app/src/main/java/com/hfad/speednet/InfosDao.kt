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


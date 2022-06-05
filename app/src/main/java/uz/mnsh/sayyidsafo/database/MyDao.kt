package uz.mnsh.sayyidsafo.database

import androidx.room.*
import uz.ilhomjon.sayyidsafo.network.models.Data

@Dao
interface MyDao {
    @Insert
    fun addUsers(data: Data)

    @Delete
    suspend fun deleteAudio(data: Data)

    @Query("select * from data where topic_id =:id")
    suspend fun getAllUsers(id:Int): List<Data>

    @Query("select id from Data")
    fun getIdList():List<Int>

    @Query("select * from Data where id =:id")
    fun getDataById(id: Int):Data
}
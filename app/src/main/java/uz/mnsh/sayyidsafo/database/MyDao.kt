package uz.mnsh.sayyidsafo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.ilhomjon.sayyidsafo.network.models.Data

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(data: List<Data>)

    @Query("select * from data where topic_id =:id")
    suspend fun getAllUsers(id:Int): List<Data>
}
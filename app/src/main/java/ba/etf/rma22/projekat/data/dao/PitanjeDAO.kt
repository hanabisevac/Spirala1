package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Pitanje


@Dao
interface PitanjeDAO {

    @Query("SELECT * FROM Pitanje")
    suspend fun getAllPitanja() : List<Pitanje>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPitanje(vararg pitanje : Pitanje)

    @Query("SELECT * FROM Pitanje WHERE id = :id")
    suspend fun getPitanjeById(id : Int) : Pitanje

    @Query("DELETE FROM Pitanje")
    suspend fun deletePitanja()





}
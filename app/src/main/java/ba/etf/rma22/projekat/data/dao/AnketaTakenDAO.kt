package ba.etf.rma22.projekat.data.dao

import androidx.room.*
import ba.etf.rma22.projekat.data.models.AnketaTaken

@Dao
interface AnketaTakenDAO {

    @Query("SELECT * FROM AnketaTaken WHERE id = :id")
    suspend fun getAnketaTakenById(id : Int) : AnketaTaken

    @Insert
    suspend fun insertAnketaTaken(vararg ankete : AnketaTaken)


    @Query("SELECT * FROM AnketaTaken WHERE AnketumId = :anketumId")
    suspend fun getAnketaTakenByAnketumId(anketumId : Int) : AnketaTaken


    @Query("UPDATE AnketaTaken SET progres = :progres WHERE id = :id")
    suspend fun updateAnketaTaken(progres : Int, id : Int)

    @Query("DELETE FROM AnketaTaken")
    suspend fun deleteAnketeTaken()

    @Query("SELECT * FROM AnketaTaken")
    suspend fun getAllAnketaTaken() : List<AnketaTaken>
   //dodat delete da se sve izbrise



}
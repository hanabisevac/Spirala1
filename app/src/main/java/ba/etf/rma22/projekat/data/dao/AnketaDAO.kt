package ba.etf.rma22.projekat.data.dao

import androidx.room.*
import ba.etf.rma22.projekat.data.models.Anketa

@Dao
interface AnketaDAO {

    @Query("SELECT * FROM Anketa")
    suspend fun getAll() : List<Anketa>

    @Query("SELECT * FROM Anketa WHERE id = :id")
    suspend fun getById(id : Int) : Anketa


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg ankete : Anketa)

    @Query("UPDATE Anketa SET progres = :progres WHERE id = :id")
    suspend fun updateAnketu(progres : Int, id : Int)

    @Query("DELETE FROM Anketa")
    suspend fun deleteAnkete()


}
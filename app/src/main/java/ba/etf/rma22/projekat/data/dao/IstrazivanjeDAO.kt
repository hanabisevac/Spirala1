package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Istrazivanje


@Dao
interface IstrazivanjeDAO {

    @Query("SELECT * FROM Istrazivanje")
    suspend fun getAllIstrazivanja() : List<Istrazivanje>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIstrazivanja(vararg istrazivanje: Istrazivanje)

    @Query("SELECT * FROM Istrazivanje WHERE id = :id")
    suspend fun getIstrazivanjeById(id : Int) : Istrazivanje

    @Query("DELETE FROM Istrazivanje")
    suspend fun deleteIstrazivanja()
}
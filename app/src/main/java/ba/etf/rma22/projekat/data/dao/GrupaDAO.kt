package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Grupa


@Dao
interface GrupaDAO {
    @Query("SELECT * FROM Grupa")
    suspend fun getAllGrupe() : List<Grupa>

    @Query("SELECT * FROM Grupa WHERE id = :id")
    suspend fun getGrupaById(id : Int) : Grupa

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrupe(vararg grupe : Grupa)

    @Query("SELECT * FROM Grupa WHERE IstrazivanjeId = :istrazivanjeId")
    suspend fun getGrupeZaIstrazivanje(istrazivanjeId : Int) : List<Grupa>

    @Query("DELETE FROM Grupa")
    suspend fun deleteGrupe()

    @Query("UPDATE Grupa SET AnketaId = :AnketaId WHERE id = :id")
    suspend fun updateGrupu(AnketaId : String, id : Int)
}
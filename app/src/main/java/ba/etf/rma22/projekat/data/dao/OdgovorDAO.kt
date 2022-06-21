package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Odgovor


@Dao
interface OdgovorDAO {

    @Query("SELECT * FROM Odgovor")
    suspend fun getAllOdgovori() : List<Odgovor>

    @Insert
    suspend fun insertOdgovor(vararg odgovor : Odgovor)

    @Query("SELECT * FROM Odgovor WHERE AnketaTakenId = :anketaTakenId")
    suspend fun getOdgovoriByAnketaTakenId(anketaTakenId : Int) : Odgovor

    @Query("SELECT * FROM Odgovor WHERE PitanjeId = :pitanjeId")
    suspend fun getOdgovoriZaPitanjeId(pitanjeId : Int) : List<Odgovor>

    @Query("DELETE FROM Odgovor")
    suspend fun deleteOdgovori()
}
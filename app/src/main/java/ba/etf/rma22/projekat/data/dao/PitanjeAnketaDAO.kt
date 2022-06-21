package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.PitanjeAnketa


@Dao
interface PitanjeAnketaDAO {

    @Query("SELECT * FROM PitanjeAnketa")
    suspend fun getAllPitanjeAnketa() : List<PitanjeAnketa>

    @Insert
    suspend fun insertPitanjeAnketa(vararg pitanjeAnketa: PitanjeAnketa)

    @Query("SELECT * FROM PitanjeAnketa WHERE AnketumId = :anketumId")
    suspend fun getPitanjeAnketaByAnketumId(anketumId : Int) : List<PitanjeAnketa>

    @Query("DELETE FROM PitanjeAnketa")
    suspend fun deletePitanjaAnkete()


}
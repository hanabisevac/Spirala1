package ba.etf.rma22.projekat.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Account

@Dao
interface AccountDAO {
    @Insert
    suspend fun insertNovi(acc : Account)

    @Query("SELECT * FROM Account")
    suspend fun getAccount() : Account

    @Query("SELECT acHash FROM Account")
    suspend fun getAcHash() : String

    @Query("DELETE FROM Account")
    suspend fun deleteAccount()



}
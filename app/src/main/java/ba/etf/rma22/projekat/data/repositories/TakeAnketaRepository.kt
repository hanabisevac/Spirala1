package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TakeAnketaRepository {


    suspend fun zapocniAnketu(idAnketa : Int) : AnketaTaken?{
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.pocniOdg(AccountRepository.acHash, idAnketa)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }

    suspend fun getPoceteAnkete() : List<AnketaTaken> ?  {
        return withContext(Dispatchers.IO) {
            val response = ApiConfig.retrofit.getPokusajAnketa(AccountRepository.acHash)
            if(response.body()!!.isEmpty()) return@withContext  null
            return@withContext response.body()
        }
    }
}
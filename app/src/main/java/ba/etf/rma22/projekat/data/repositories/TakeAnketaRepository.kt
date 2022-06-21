package ba.etf.rma22.projekat.data.repositories


import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object  TakeAnketaRepository {


    suspend fun zapocniAnketu(idAnketa : Int) : AnketaTaken? {
        return withContext(Dispatchers.IO){
            val provjera = getPoceteAnkete()
            if(provjera!=null){
                for(i in provjera.indices){
                    if(provjera[i].AnketumId == idAnketa){
                        return@withContext provjera[i]
                    }
                }
            }
            val response = ApiConfig.retrofit.pocniOdg(AccountRepository.acHash, idAnketa)
            val responseBody = response.body()
            val db = AppDatabase.getInstance(ContextRepo.getContext())
            //dodajemo novu zapocetu anketu u bazu
            when(responseBody){
                is AnketaTaken -> {
                    println("Ovo je rez "+AnketaRepository.getById(idAnketa))
                    if(AnketaRepository.getById(idAnketa)!!.naziv != null) db.anketaTakenDAO().insertAnketaTaken(responseBody!!)
                    return@withContext responseBody
                }
                else -> return@withContext null
            }
        }
    }

    suspend fun getPoceteAnkete() : List<AnketaTaken> ?  {
        return withContext(Dispatchers.IO) {
            val response = ApiConfig.retrofit.getPokusajAnketa(AccountRepository.acHash)
            val responseBody = response.body()
            if(responseBody!!.isEmpty()) return@withContext  null
            return@withContext responseBody
        }
    }
}
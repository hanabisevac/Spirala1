package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeIGrupaRepository {

    //vraća sva istraživanja ili ako je zadan offset odgovarajući page u rezultatima
    suspend fun getIstrazivanja(offset : Int?) : List<Istrazivanje> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getIstrazivanje(offset)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    //kada offset nije zadan
    suspend fun getIstrazivanja() : List<Istrazivanje> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getIstrazivanje(1)
            val response1 = ApiConfig.retrofit.getIstrazivanje(2)
            val responseBody2 = response1.body()
            val responseBody = response.body()
            val lista = mutableListOf<Istrazivanje>()
            lista.addAll(responseBody!!)
            lista.addAll(responseBody2!!)
            return@withContext lista
        }
    }



    //vraća sve grupe
    suspend fun getGrupe() : List<Grupa> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getAllGrupe()
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }


    //vraća grupe na istraživanju sa id-em idIstrazivanja
    suspend fun getGrupeZaIstrazivanje(idIstrazivanja : Int) : List<Grupa>{
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getAllGrupe()
            val responseBody = response.body()
            val lista = mutableListOf<Grupa>()
            for(i in responseBody?.indices!!){
                if(responseBody[i].istrazivanjeId == idIstrazivanja) lista.add(responseBody[i])
            }
            return@withContext lista
        }
    }


    //upisuje studenta u grupu sa id-em idGrupa i vraća true ili vraća false ako nije moguće upisati studenta
    suspend fun upisiUGrupu(idGrupa : Int) : Boolean{
        return withContext(Dispatchers.IO) {
            val response = ApiConfig.retrofit.dodajStudenta(idGrupa, AccountRepository.acHash)
            val responseBody = response.body()
            if(responseBody?.poruka?.startsWith("Ne postoji")!! || responseBody.poruka == "Grupa not found.") return@withContext false
            return@withContext true
        }
    }


    //vraća grupe u kojima je student upisan
    suspend fun getUpisaneGrupe() : List<Grupa> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getStudentoveGrupe(AccountRepository.acHash)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

}
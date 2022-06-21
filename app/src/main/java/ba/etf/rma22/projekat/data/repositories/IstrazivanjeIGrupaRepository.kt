package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.AppDatabase
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
            val db = AppDatabase.getInstance(ContextRepo.getContext())
            if(db.istrazivanjeDAO().getAllIstrazivanja().isEmpty()) responseBody!!.forEach { s -> db.istrazivanjeDAO().insertIstrazivanja(s) }
            return@withContext responseBody!!
        }
    }

    //kada offset nije zadan
    suspend fun getIstrazivanja() : List<Istrazivanje> {
        return withContext(Dispatchers.IO){
            val lista = mutableListOf<Istrazivanje>()
            var brojac = 1
            while(true) {
                val response = ApiConfig.retrofit.getIstrazivanje(brojac)
                val responseBody = response.body()
                lista.addAll(responseBody!!)
                if(responseBody.size<5) break
                brojac++
            }
            val db = AppDatabase.getInstance(ContextRepo.getContext())
            if(db.istrazivanjeDAO().getAllIstrazivanja().isEmpty()) lista.forEach {  s -> db.istrazivanjeDAO().insertIstrazivanja(s) }
            return@withContext lista
        }
    }

    suspend fun getIstrazivanjeZaGrupe(idGrupe : Int) : Istrazivanje ?{
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getIstrazivanjeZaGrupu(idGrupe)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }



    //vraća sve grupe
    suspend fun getGrupe() : List<Grupa> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getAllGrupe()
            val responseBody = response.body()
            for(i in responseBody!!.indices){
                val istrazivanje = getIstrazivanjeZaGrupe(responseBody[i].id)
                responseBody[i].nazivIstrazivanja = istrazivanje!!.naziv
            }
            val db = AppDatabase.getInstance(ContextRepo.getContext())
            responseBody.forEach {  g -> db.grupaDAO().insertGrupe(g) }
            return@withContext responseBody!!
        }
    }

    suspend fun getUpisaneGrupe() : List<Grupa> {
        return withContext(Dispatchers.IO) {
            val response = ApiConfig.retrofit.getStudentoveGrupe(AccountRepository.acHash)
            val grupe = response.body()
            return@withContext grupe!!
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
            val db = AppDatabase.getInstance(ContextRepo.getContext())
            if(responseBody?.poruka?.startsWith("Ne postoji")!! || responseBody.poruka == "Grupa not found.") return@withContext false
            val grupa = ApiConfig.retrofit.getGrupuSaId(idGrupa)
            val grupaBody = grupa.body()
            val istrazivanje = getIstrazivanjeZaGrupe(idGrupa)
            grupaBody?.nazivIstrazivanja = istrazivanje!!.naziv
            //dodajemo novu grupu u koju se korisnik upisao u bazu
            //db.grupaDAO().insertGrupe(grupaBody!!)
            //dodajemo ankete koje pripadaju toj grupi
            //AnketaRepository.getAnketaZaGrupu(grupaBody).forEach { ank -> db.anketaDAO().insertAll(ank) }
            val lista = AnketaRepository.getAnketaZaGrupu(grupaBody!!)
            //dodajemo pitanja koja pripadaju tim anketama
            for(i in lista.indices) {
                val pitanja = PitanjeAnketaRepository.getPitanja(lista[i].id)
                pitanja!!.forEach { p ->
                    val string = p.opcije.joinToString(" ")
                    p.stringOpcije = string
                    db.pitanjeDAO().insertPitanje(p)  }
            }
            //dodajemo istrazivanje za tu grupu
            //db.istrazivanjeDAO().insertIstrazivanja(istrazivanje)
            return@withContext true
        }
    }


    //vraća grupe u kojima je student upisan
    suspend fun getUpisaneGrupeApi() : List<Grupa> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getStudentoveGrupe(AccountRepository.acHash)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun dajGrupeZaAnketu(id : Int) : List<Grupa> ?{
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getGrupeZaDostupneAnkete(id)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }

}
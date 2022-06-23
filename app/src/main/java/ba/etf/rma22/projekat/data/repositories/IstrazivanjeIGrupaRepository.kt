package ba.etf.rma22.projekat.data.repositories


import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeIGrupaRepository {


    //vraća sva istraživanja ili ako je zadan offset odgovarajući page u rezultatima
    suspend fun getIstrazivanja(offset : Int?) : List<Istrazivanje> ?{
        return withContext(Dispatchers.IO){
            try{
                val response = ApiConfig.retrofit.getIstrazivanje(offset)
                val responseBody = response.body()
                val db = AppDatabase.getInstance(ContextRepo.getContext())
                if (db.istrazivanjeDAO().getAllIstrazivanja()
                        .isEmpty()
                ) responseBody!!.forEach { s -> db.istrazivanjeDAO().insertIstrazivanja(s) }
                return@withContext responseBody!!
            }catch(error : Exception){
                println(error.toString())
                return@withContext  null
            }
        }
    }

    //kada offset nije zadan
    suspend fun getIstrazivanja() : List<Istrazivanje> ?{
        return withContext(Dispatchers.IO){
            try{
                val lista = mutableListOf<Istrazivanje>()
                var brojac = 1
                while (true) {
                    val response = ApiConfig.retrofit.getIstrazivanje(brojac)
                    val responseBody = response.body()
                    lista.addAll(responseBody!!)
                    if (responseBody.size < 5) break
                    brojac++
                }
                val db = AppDatabase.getInstance(ContextRepo.getContext())
                if (db.istrazivanjeDAO().getAllIstrazivanja()
                        .isEmpty()
                ) lista.forEach { s -> db.istrazivanjeDAO().insertIstrazivanja(s) }
                return@withContext lista
            }catch(error : Exception){
                println(error.toString())
                return@withContext  null
            }
        }
    }

    suspend fun getIstrazivanjeZaGrupe(idGrupe : Int) : Istrazivanje ?{
        return withContext(Dispatchers.IO){
            try{
                val response = ApiConfig.retrofit.getIstrazivanjeZaGrupu(idGrupe)
                val responseBody = response.body()
                return@withContext responseBody
            }catch(error : Exception){
                println(error.toString())
                return@withContext  null
            }
        }
    }



    //vraća sve grupe
    suspend fun getGrupe() : List<Grupa> ?{
        return withContext(Dispatchers.IO){
            try{
                val response = ApiConfig.retrofit.getAllGrupe()
                val responseBody = response.body()
                for (i in responseBody!!.indices) {
                    val istrazivanje = getIstrazivanjeZaGrupe(responseBody[i].id)
                    responseBody[i].nazivIstrazivanja = istrazivanje!!.naziv
                }
                val db = AppDatabase.getInstance(ContextRepo.getContext())
                responseBody.forEach { g -> db.grupaDAO().insertGrupe(g) }
                for (i in responseBody.indices) {
                    val g = AnketaRepository.getDostupneAnketeZaGrupu(responseBody[i].id)
                    var string: String = ""
                    val lista = mutableListOf<Int>()
                    g!!.forEach { a -> lista.add(a.id) }
                    string = lista.joinToString(",")
                    db.grupaDAO().updateGrupu(string, responseBody[i].id)
                }
                val upisane = getUpisaneGrupe()
                val bazaGrupe = db.grupaDAO().getAllGrupe()
                for(i in upisane!!.indices){
                    for(j in bazaGrupe.indices){
                        if(upisane[i].id == bazaGrupe[j].id) db.grupaDAO().updateUpisan(upisane[i].id)
                    }
                }
                return@withContext responseBody!!
            }catch(error : Exception){
                println(error.toString())
                return@withContext  null
            }
        }
    }

    suspend fun getUpisaneGrupe() : List<Grupa>? {
        return withContext(Dispatchers.IO) {
            try{
                val response = ApiConfig.retrofit.getStudentoveGrupe(AccountRepository.acHash)
                val grupe = response.body()
                return@withContext grupe!!
            }catch(error : Exception){
                println(error.toString())
                return@withContext  null
            }
        }
    }


    //vraća grupe na istraživanju sa id-em idIstrazivanja
    suspend fun getGrupeZaIstrazivanje(idIstrazivanja : Int) : List<Grupa>?{
        return withContext(Dispatchers.IO){
            try{
                val response = ApiConfig.retrofit.getAllGrupe()
                val responseBody = response.body()
                val lista = mutableListOf<Grupa>()
                for (i in responseBody?.indices!!) {
                    if (responseBody[i].istrazivanjeId == idIstrazivanja) lista.add(responseBody[i])
                }
                return@withContext lista
            }catch(error : Exception){
                println(error.toString())
                return@withContext  null
            }
        }
    }


    //upisuje studenta u grupu sa id-em idGrupa i vraća true ili vraća false ako nije moguće upisati studenta
    suspend fun upisiUGrupu(idGrupa : Int) : Boolean ?{
        return withContext(Dispatchers.IO) {
            try{
                val response = ApiConfig.retrofit.dodajStudenta(idGrupa, AccountRepository.acHash)
                val responseBody = response.body()
                val db = AppDatabase.getInstance(ContextRepo.getContext())
                if (responseBody?.poruka?.startsWith("Ne postoji")!! || responseBody.poruka == "Grupa not found.") return@withContext false
                val grupa = ApiConfig.retrofit.getGrupuSaId(idGrupa)
                val grupaBody = grupa.body()
                val istrazivanje = getIstrazivanjeZaGrupe(idGrupa)
                grupaBody?.nazivIstrazivanja = istrazivanje!!.naziv
                val lista = AnketaRepository.getAnketaZaGrupu(grupaBody!!)
                //dodajemo pitanja koja pripadaju tim anketama
                for (i in lista!!.indices) {
                    val pitanja = PitanjeAnketaRepository.getPitanja(lista[i].id)
                    pitanja!!.forEach { p ->
                        val string = p.opcije.joinToString(" ")
                        p.stringOpcije = string
                        db.pitanjeDAO().insertPitanje(p)
                    }
                }
                db.grupaDAO().updateUpisan(idGrupa)
                return@withContext true
            }catch(error : Exception){
                println(error.toString())
                return@withContext  null
            }
        }
    }


    suspend fun getUpisaneGrupeBaza() : List<Grupa> {
        return withContext(Dispatchers.IO){
            val db = AppDatabase.getInstance(ContextRepo.getContext())
            val grupe = db.grupaDAO().getAllGrupe()
            val lista = mutableListOf<Grupa>()
            for(i in grupe.indices){
                if(grupe[i].upisana == 1) lista.add(grupe[i])
            }
            return@withContext lista
        }
    }

    suspend fun dajGrupeZaAnketu(id : Int) : List<Grupa> ?{
        return withContext(Dispatchers.IO){
            try{
                val response = ApiConfig.retrofit.getGrupeZaDostupneAnkete(id)
                val responseBody = response.body()
                return@withContext responseBody
            }catch(error : Exception){
                println(error.toString())
                return@withContext  null
            }
        }
    }

}
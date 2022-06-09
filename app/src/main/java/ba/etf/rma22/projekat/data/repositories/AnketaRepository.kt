package ba.etf.rma22.projekat.data.repositories


import ba.etf.rma22.projekat.data.models.Anketa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.ThreadPoolExecutor

object AnketaRepository {


    suspend fun getAll() : List<Anketa> {
        return withContext(Dispatchers.IO){
            var lista = mutableListOf<Anketa>()
            var brojac = 1
            while(true){
                val response = ApiConfig.retrofit.getAnkete(brojac)
                val responseBody = response.body()
                lista.addAll(responseBody!!)
                if(responseBody.size<5) break
                brojac++
            }
            return@withContext lista
        }
    }

    suspend fun getDostupneAnketeZaGrupu(id : Int) : List<Anketa> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getAnketeSaGrupom(id)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }




    suspend fun getAll(offset : Int) : List<Anketa> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getAnkete(offset)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun getById(id : Int) : Anketa{
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getAnketaSaId(id)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun dajSveAnkete() : List<Anketa> {
        return withContext(Dispatchers.IO){
            val sve = getAll()
            val lista = mutableListOf<Anketa>()
            for(i in sve.indices){
                val g = IstrazivanjeIGrupaRepository.dajGrupeZaAnketu(sve[i].id)
                for(j in g!!.indices){
                    sve[i].nazivGrupe = g[j].naziv
                    sve[i].nazivIstrazivanja = IstrazivanjeIGrupaRepository.getIstrazivanjeZaGrupe(g[j].id)!!.naziv
                    lista.add(sve[i])
                }
            }
            sve.stream().forEach { ank -> ank.progres = 0 }
            val zapocete = TakeAnketaRepository.getPoceteAnkete()
            if(zapocete != null){
                for(i in lista.indices){
                    for(j in zapocete.indices){
                        if(zapocete[j].AnketumId == lista[i].id){
                            lista[i].progres = zapocete[j].progres
                            if(lista[i].progres == 100) lista[i].datumRada = zapocete[j].datumRada
                        }
                    }
                }
            }
            return@withContext lista
        }
    }




    //vraca listu anketa na kojima je student
    suspend fun getUpisane() : List<Anketa> {
         return withContext(Dispatchers.IO) {
             val response = ApiConfig.retrofit.getStudentoveGrupe(AccountRepository.acHash)
             val responseBody = response.body()
             val listaAnketa = mutableListOf<Anketa>()
             val pocete = TakeAnketaRepository.getPoceteAnkete()
             for(i in responseBody!!.indices){
                 val a = getDostupneAnketeZaGrupu(responseBody[i].id)
                 val naziv = IstrazivanjeIGrupaRepository.getIstrazivanjeZaGrupe(responseBody[i].id)!!.naziv
                 a.stream().forEach { ank -> ank.nazivGrupe = responseBody[i].naziv }
                 a.stream().forEach { ank -> ank.nazivIstrazivanja = naziv }
                 a.stream().forEach { ank -> ank.progres = 0 }
                 listaAnketa.addAll(a)
             }

             if(pocete != null){
                 for(i in listaAnketa.indices){
                     for(j in pocete.indices){
                         if(listaAnketa[i].id == pocete[j].AnketumId){
                             listaAnketa[i].progres = pocete[j].progres
                             if(listaAnketa[i].progres == 100) listaAnketa[i].datumRada = pocete[j].datumRada
                             break
                         }
                     }
                 }
             }
             return@withContext listaAnketa
         }
    }


    suspend fun getDone() : List<Anketa> {
        return withContext(Dispatchers.IO){
            val response = getUpisane()
            val lista = mutableListOf<Anketa>()
            for(i in response.indices){
                if(response[i].datumRada != null) lista.add(response[i])
            }
            return@withContext lista
        }
    }

    suspend fun getFuture() : List<Anketa> {
        return withContext(Dispatchers.IO){
            val response = getUpisane()
            val lista = mutableListOf<Anketa>()
            for(i in response.indices){
                if(response[i].datumRada == null && (response[i].datumKraj == null || response[i].datumKraj!!.after(Date())))
                    lista.add(response[i])
            }
            return@withContext lista
        }
    }

    suspend fun getPast() : List<Anketa> {
        return withContext(Dispatchers.IO){
            val response = getUpisane()
            val lista = mutableListOf<Anketa>()
            for(i in response.indices) {
                if(response[i].datumKraj!=null && response[i].datumRada == null && response[i].datumKraj!!.before(Date()))
                    lista.add(response[i])
            }
            return@withContext lista
        }
    }

}
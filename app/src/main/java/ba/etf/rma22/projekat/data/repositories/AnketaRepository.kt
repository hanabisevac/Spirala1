package ba.etf.rma22.projekat.data.repositories


import android.content.Context
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
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
            val db = AppDatabase.getInstance(ContextRepo.getContext())
            lista.forEach { ank -> db.anketaDAO().insertAll(ank) }
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

    //iz baze

    suspend fun getMyAnkete() : List<Anketa> {
        return withContext(Dispatchers.IO){
            val db = AppDatabase.getInstance(ContextRepo.getContext())
            val ankete = db.anketaDAO().getAll()
            return@withContext ankete
        }
    }

    suspend fun dodajAnketu(context: Context, anketa : Anketa) : String {
        return withContext(Dispatchers.IO){
            val db = AppDatabase.getInstance(context)
            db.anketaDAO().insertAll(anketa)
            return@withContext "Upisana"
        }
    }

    /*suspend fun getDone() : List<Anketa> {
        return withContext(Dispatchers.IO) {
            val lista = getMyAnkete()
            val gotove = mutableListOf<Anketa>()
            for(i in lista.indices){
                if(lista[i].datumRada != null) gotove.add(lista[i])
            }
            return@withContext gotove
        }
    }

    suspend fun getFuture() : List<Anketa> {
        return withContext(Dispatchers.IO) {
            val lista = getMyAnkete()
            val buduce = mutableListOf<Anketa>()
            for(i in buduce.indices){
                if(lista[i].dajDatumPocetak().after(Date()) || (lista[i].datumRada == null && (lista[i].datumKraj == null || lista[i].dajDatumKraj()!!.after(Date())))) buduce.add(lista[i])
            }
            return@withContext buduce
        }
    }

    suspend fun getPast() : List<Anketa> {
        return withContext(Dispatchers.IO) {
            val lista = getMyAnkete()
            val prosle = mutableListOf<Anketa>()
            for(i in lista.indices){
                if(lista[i].datumKraj!=null && lista[i].dajDatumKraj()!!.before(Date())) prosle.add(lista[i])
            }
            return@withContext prosle
        }
    }*/


    suspend fun getAll(offset : Int) : List<Anketa> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getAnkete(offset)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun getById(id : Int) : Anketa ?{
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getAnketaSaId(id)
            val responseBody = response.body()
            when(responseBody){
                is Anketa -> Unit
                else -> return@withContext null
            }
            return@withContext responseBody
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
            val db = AppDatabase.getInstance(ContextRepo.getContext())
            lista.forEach { ank -> db.anketaDAO().insertAll(ank) }
            return@withContext lista
        }
    }



    suspend fun getAnketaZaGrupu(grupa : Grupa) : List<Anketa> {
        return withContext(Dispatchers.IO){
            val response = getDostupneAnketeZaGrupu(grupa.id)
            val naziv = IstrazivanjeIGrupaRepository.getIstrazivanjeZaGrupe(grupa.id)!!.naziv
            for (i in response.indices){
                response[i].nazivGrupe = grupa.naziv
                response[i].nazivIstrazivanja = naziv
            }
            response.stream().forEach { ank -> ank.progres = 0 }
            val zapocete = TakeAnketaRepository.getPoceteAnkete()
            if(zapocete != null){
                for(i in response.indices){
                    for(j in zapocete.indices){
                        if(zapocete[j].AnketumId == response[i].id){
                            response[i].progres = zapocete[j].progres
                            if(response[i].progres == 100) response[i].datumRada = zapocete[j].datumRada
                        }
                    }
                }
            }
            return@withContext response

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
                if(response[i].datumRada == null && (response[i].datumKraj == null || response[i].dajDatumKraj()!!.after(Date())))
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
                if(response[i].datumKraj!=null && response[i].datumRada == null && response[i].dajDatumKraj()!!.before(Date()))
                    lista.add(response[i])
            }
            return@withContext lista
        }
    }

}
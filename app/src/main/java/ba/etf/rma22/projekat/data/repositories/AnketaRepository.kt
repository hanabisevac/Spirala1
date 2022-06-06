package ba.etf.rma22.projekat.data.repositories


import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.ThreadPoolExecutor

object AnketaRepository {


    suspend fun getAll() : List<Anketa> {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.getAnkete(1)
            val response1 = ApiConfig.retrofit.getAnkete(2)
            val responseBody1 = response1.body()
            val responseBody = response.body()
            var lista = mutableListOf<Anketa>()
            lista.addAll(responseBody!!)
            lista.addAll(responseBody1!!)
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

    suspend fun dodajPodatkeAnkete() : List<Anketa> {
        return withContext(Dispatchers.IO)
        {
            val lista = mutableListOf<Anketa>()
            val grupe = GrupaRepository.getAll()
            for (i in grupe.indices) {
                val ankete = getDostupneAnketeZaGrupu(grupe[i].id)
                for (j in ankete.indices) {
                    ankete[j].nazivGrupe = grupe[i].naziv
                    lista.add(ankete[j])
                }
            }

            return@withContext lista
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

    //vraca listu anketa na kojima je student
     suspend fun getUpisane() : List<Anketa> {
         return withContext(Dispatchers.IO) {
             val response = ApiConfig.retrofit.getStudentoveGrupe(AccountRepository.acHash)
             val responseBody = response.body()
             val listaAnketa = mutableListOf<Anketa>()
             for(i in responseBody!!.indices){
                 listaAnketa.addAll(getDostupneAnketeZaGrupu(responseBody[i].id))
             }
             //ne znamo dal je ovo potrebno jos uvijek
             val rezultat = listaAnketa.toSet().toList()
             return@withContext rezultat
         }
    }






    /*
    fun getDone() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getMyAnkete()
        val pomocna2 = dajAnketeSaGrupom()
        for(i in pomocna.indices){
            if(pomocna[i].datumRada != null) lista.add(pomocna[i])
        }
        for(i in pomocna2.indices){
            if(pomocna2[i].datumRada != null) lista.add(pomocna2[i])
        }
        return lista
    }


    fun dajSveBezMojih() : List<Anketa> {
        val korisnikove = KorisnikRepository.getGrupe()
        val sve = listaAnketa()
        val lista = mutableListOf<Anketa>()
        var ima : Int = 0
        for(i in sve.indices){
            for(j in korisnikove.indices){
                if(sve[i].nazivIstrazivanja == korisnikove[j].nazivIstrazivanja && sve[i].nazivGrupe == korisnikove[j].naziv) ima=1
            }
            if(ima==1){
                ima=0
            }
            else{
                lista.add(sve[i])
            }
        }
        return lista
    }

    fun dajAnketeSaGrupom() : List<Anketa> {
        var lista = mutableListOf<Anketa>()
        val grupa = KorisnikRepository.getGrupe()
        val sve = dajSveBezMojih()
        var ima : Int = 0
        for(i in sve.indices) {
            for (j in grupa.indices) {
                if (sve[i].nazivGrupe == grupa[j].naziv ) ima=1
            }
            if(ima == 1){
                ima = 0
                lista.add(sve[i])
            }
        }
        return lista
    }


    fun aktivneAnkete() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getMyAnkete()
        val pomocna2 = dajAnketeSaGrupom()
        for(i in pomocna.indices){
            if(pomocna[i].datumRada == null && pomocna[i].datumPocetak.before(Date()) && pomocna[i].datumKraj.after(Date())) lista.add(pomocna.get(i))
        }
        for(i in pomocna2.indices){
            if(pomocna2[i].datumRada == null && pomocna2[i].datumPocetak.before(Date()) && pomocna2[i].datumKraj.after(Date())) lista.add(pomocna2.get(i))
        }
        return lista
    }

    fun getNotTaken() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getMyAnkete()
        val pomocna2 = dajAnketeSaGrupom()
        for(i in pomocna.indices){
            if(pomocna[i].datumRada == null && pomocna[i].datumKraj.before(Date())) lista.add(
                pomocna[i]
            )
        }
        for(i in pomocna2.indices){
            if(pomocna2[i].datumRada == null && pomocna2[i].datumKraj.before(Date())) lista.add(
                pomocna2[i]
            )
        }
        return lista

    }
    
    fun getMyAnkete() : List<Anketa> {
        val lista = KorisnikRepository.getGrupe()
        val ankete = listaAnketa()
        val nova = mutableListOf<Anketa>()
        var ima : Int = 0
        for(i in ankete.indices){
            for(j in lista.indices){
                if(lista[j].naziv == ankete[i].nazivGrupe && lista[j].nazivIstrazivanja == ankete[i].nazivIstrazivanja) ima=1;
            }
            if(ima == 1){
                ima = 0
                nova.add(ankete[i])
            }
        }
        return nova
    }

    fun getFuture() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getMyAnkete()
        val pomocna2 = dajAnketeSaGrupom()
        for(i in pomocna.indices){
            if(pomocna[i].datumPocetak.after(Date())) lista.add(pomocna[i])
        }
        for(i in pomocna2.indices){
            if(pomocna2[i].datumPocetak.after(Date())) lista.add(pomocna2[i])

        }
        return lista

    }


    fun getAnketu(naziv : String, istrazivanje : String) : Anketa? {
        val sve = getAll()
        for(i in sve.indices) if(sve[i].naziv == naziv && sve[i].nazivIstrazivanja == istrazivanje) return sve[i]
        return null
    }
    */



}
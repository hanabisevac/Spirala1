package ba.etf.rma22.projekat.data.repositories



import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.OdgovorBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.floor
import kotlin.math.round

object OdgovorRepository {

    suspend fun getOdgovoriAnketa(idAnketa : Int) : List<Odgovor> ?{
        return withContext(Dispatchers.IO){
            val anketeTaken = TakeAnketaRepository.getPoceteAnkete()
            var id : Int = 0
            if(anketeTaken != null){
                for(i in anketeTaken.indices){
                    if(anketeTaken[i].AnketumId == idAnketa) id = anketeTaken[i].id
                }
            }
            val response = ApiConfig.retrofit.getPokusajRjesavanja(AccountRepository.acHash, id)
            val responseBody = response.body()
            return@withContext responseBody
        }

    }
    //ovo je post
    suspend fun postaviOdgovorAnketa(idAnketaTaken : Int, idPitanje : Int, odgovor : Int) : Int{
        return withContext(Dispatchers.IO){
            val anketa = TakeAnketaRepository.getPoceteAnkete()
            //val odgovori = getOdgovoriAnketa(idAnketaTaken)
            var progres = 0
            lateinit var a : AnketaTaken
            //var prog = TrenutnaAnketaRepository.dajProgres()
            if(anketa != null){
                for(i in anketa.indices){
                    if(idAnketaTaken == anketa[i].id) {
                        val pitanja = PitanjeAnketaRepository.getPitanja(anketa[i].AnketumId)
                        progres = anketa[i].progres
                        var prog = 1.0F/ pitanja!!.size
                        var p = (round(prog*10)*10).toInt()
                        progres +=p
                        if((progres/10)%2 !=0) progres +=10
                        a = anketa[i]
                        break
                    }
                }
            }

            val odgovori = getOdgovoriAnketa(a.AnketumId)
            if(odgovori != null){
                for (i in odgovori.indices) {
                    if (odgovori[i].pitanjeId == idPitanje) {
                        progres = a.progres
                        println(progres)
                        break
                    }
                }
            }


            if(progres>100) progres = 100
            //println("Progres je "+progres)
            TrenutnaAnketaRepository.postaviProgres(progres)
            val myOdgovor = OdgovorBody(odgovor, idPitanje, progres)
            val response = ApiConfig.retrofit.addOdgovor(AccountRepository.acHash, idAnketaTaken, myOdgovor)
            return@withContext progres
        }

    }
}
package ba.etf.rma22.projekat.data.repositories



import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.OdgovorBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
            var progresF : Float = 0.0F
            var progres : Int = 0
            var velicina : Int = 0
            lateinit var a : AnketaTaken
            if(anketa != null){
                for(i in anketa.indices){
                    if(idAnketaTaken == anketa[i].id) {
                        val pitanja = PitanjeAnketaRepository.getPitanja(anketa[i].AnketumId)
                        velicina = pitanja!!.size
                        a = anketa[i]
                        break
                    }
                }
            }
            val odgovori = getOdgovoriAnketa(a.AnketumId)
            var p : Int  = 0
            if(!odgovori!!.isEmpty()){
                progresF = (odgovori.size+1).toFloat()/velicina
                p = (round(progresF*10)*10).toInt()
                if((p/10) %2 != 0) p+=10
                for (i in odgovori.indices) {
                    if (odgovori[i].pitanjeId == idPitanje) {
                        p = a.progres
                        println(progres)
                        break
                    }
                }
            }
            else {
                progresF = 1.0F/velicina
                p = (round(progresF*10)*10).toInt()
                if((p/10) %2 != 0) p+=10
            }
            progres = p
            if(progres>100) progres = 100
            TrenutnaAnketaRepository.postaviProgres(progres)
            val myOdgovor = OdgovorBody(odgovor, idPitanje, progres)
            val response = ApiConfig.retrofit.addOdgovor(AccountRepository.acHash, idAnketaTaken, myOdgovor)
            val responseBody = response.body()
            when (responseBody) {
                is Odgovor -> progres
                else -> progres = -1
            }
            return@withContext progres
        }

    }
}
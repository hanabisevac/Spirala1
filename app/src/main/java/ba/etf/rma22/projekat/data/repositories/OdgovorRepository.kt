package ba.etf.rma22.projekat.data.repositories


import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.OdgovorBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Math.round

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
            val odgovori = getOdgovoriAnketa(idAnketaTaken)
            var prog = 0
            lateinit var anketaTaken : AnketaTaken
            if(anketa != null){
                for(i in anketa.indices){
                    if(idAnketaTaken == anketa[i].id) {
                        anketaTaken = anketa[i]
                        val pitanja = PitanjeAnketaRepository.getPitanja(anketa[i].AnketumId)
                        var progres = 0.0F
                        if(odgovori!=null) progres = (odgovori.size+1).toFloat()/ pitanja!!.size
                        else progres = 1.0F/ pitanja!!.size
                        prog = (round(progres*10)*10).toInt()
                        if((round(progres *10)%2 !=0)) prog+=10
                        break
                    }
                }
            }
            for(i in odgovori!!.indices){
                if(odgovori[i].pitanjeId == idPitanje) {
                    prog = anketaTaken.progres
                    break
                }
            }
            if(prog>100) prog = 100
            TrenutnaAnketaRepository.postaviProgres(prog)
            val myOdgovor = OdgovorBody(odgovor, idPitanje, prog)
            val response = ApiConfig.retrofit.addOdgovor(AccountRepository.acHash, idAnketaTaken, myOdgovor)
            return@withContext prog
        }

    }
}
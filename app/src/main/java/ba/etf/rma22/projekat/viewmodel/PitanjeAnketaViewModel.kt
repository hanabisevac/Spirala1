package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PitanjeAnketaViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getPitanjaZaAnketu(id : Int, pitanja : (pitanjaLista : List<Pitanje> ?) -> Unit){
        scope.launch {
            val result = PitanjeAnketaRepository.getPitanja(id)
            //println(result[0].tekstPitanja)
            pitanja.invoke(result)
        }
    }


    /*fun getPitanjaZaAnketu(anketa : Anketa) : List<Pitanje> {
        return PitanjeAnketaRepository.getPitanja(anketa.naziv, anketa.nazivIstrazivanja)
    }

    fun getPitanjaAnketa(anketa : Anketa) : List<PitanjeAnketa> {
        return PitanjeAnketaRepository.dajPitanjaAnketa(anketa.naziv, anketa.nazivIstrazivanja)
    }


    fun dajPitanjeAnketuPoNazivuPitanja(naziv : String, nazivAnkete : String, nazivIstrazivanja : String) : PitanjeAnketa? {
        return PitanjeAnketaRepository.getAnketaPoNazivuPitanja(naziv, nazivAnkete, nazivIstrazivanja)
    }
    
    fun getAll() : List<PitanjeAnketa> {
        return PitanjeAnketaRepository.getSvaPitanjaAnkete()
    }

    fun getAnketu(naziv : String, istrazivanje : String) : Anketa? {
        return PitanjeAnketaRepository.getAnketaSaIstrazivanjem(naziv, istrazivanje)
    }*/


}

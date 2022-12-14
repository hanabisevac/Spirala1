package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.repositories.KonekcijaRepository
import ba.etf.rma22.projekat.data.repositories.OdgovorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OdgovorViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun upisiOdgovor(idAnketaTaken : Int, idPitanje : Int, odgovor : Int, dajProgres : (progres : Int) -> Unit) {
        scope.launch {
            val result = OdgovorRepository.postaviOdgovorAnketa(idAnketaTaken, idPitanje, odgovor)
            dajProgres.invoke(result!!)
        }
    }

    fun dajOdgovoreNaAnketu(idAnketa : Int, odgovoriList : (odgovori : List<Odgovor> ?) -> Unit){
        scope.launch {
            var result = mutableListOf<Odgovor>()
            if(KonekcijaRepository.getKonekcija()) result = OdgovorRepository.getOdgovoriAnketa(idAnketa) as MutableList<Odgovor>
            else result = OdgovorRepository.getOdgovoriAnketaBaza(idAnketa) as MutableList<Odgovor>
            odgovoriList.invoke(result)
        }
    }
}
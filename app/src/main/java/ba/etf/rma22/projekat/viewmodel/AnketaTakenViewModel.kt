package ba.etf.rma22.projekat.viewmodel


import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.repositories.KonekcijaRepository
import ba.etf.rma22.projekat.data.repositories.TakeAnketaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnketaTakenViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun zapocniAnketu(idAnkete : Int, zapocetaAnketa : (anketaTaken : AnketaTaken) -> Unit){
        scope.launch {
            val result = TakeAnketaRepository.zapocniAnketu(idAnkete)
            zapocetaAnketa.invoke(result!!)
        }
    }

    fun dajPocete(poceteAnkete : (anketa : List<AnketaTaken>) -> Unit){
        scope.launch {
            var result = mutableListOf<AnketaTaken>()
            if(KonekcijaRepository.getKonekcija()) result = TakeAnketaRepository.getPoceteAnkete() as MutableList<AnketaTaken>
            else result = TakeAnketaRepository.getPoceteAnketeBaza() as MutableList<AnketaTaken>
            poceteAnkete.invoke(result)
        }
    }

    fun getPocetu(id : Int, pocetaAnketa : (anketa : AnketaTaken) -> Unit){
        scope.launch {
            val result = TakeAnketaRepository.getPocetuAnketuIzBaza(id)
            pocetaAnketa.invoke(result)
        }
    }

}
package ba.etf.rma22.projekat.viewmodel


import ba.etf.rma22.projekat.data.models.Pitanje
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
            pitanja.invoke(result)
        }
    }





}

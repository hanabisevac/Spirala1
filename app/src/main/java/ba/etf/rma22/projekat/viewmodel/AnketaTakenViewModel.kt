package ba.etf.rma22.projekat.viewmodel

import android.net.NetworkInfo
import ba.etf.rma22.projekat.data.models.AnketaTaken
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

    fun dajPocete(poceteAnkete : (anketa : List<AnketaTaken>) -> Unit, greska : ()->Unit){
        scope.launch {
            val result = TakeAnketaRepository.getPoceteAnkete()
            when(result){
                is List<AnketaTaken> -> poceteAnkete.invoke(result)
                else -> greska.invoke()
            }
        }
    }


}
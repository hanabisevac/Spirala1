package ba.etf.rma22.projekat.viewmodel


import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class AnketaViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)


    fun dajSve(sveAnkete : (ankete : List<Anketa>) -> Unit){
        scope.launch {
            var result = mutableListOf<Anketa>()
            if(KonekcijaRepository.getKonekcija()) result = AnketaRepository.dajSveAnkete() as MutableList<Anketa>
            else {
                result = AnketaRepository.dajSveDb() as MutableList<Anketa>
                println("usao")
                println("velicina "+result.size)
                }

            sveAnkete.invoke(result)
        }
    }

    fun getUpisane(upisane : (ankete : List<Anketa>) -> Unit) {
        scope.launch {
            val result = AnketaRepository.getUpisane()
            upisane.invoke(result!!)
        }
    }

    fun getUradjene(uradjene : (ankete: List<Anketa>) -> Unit) {
        scope.launch {
            val result = AnketaRepository.getDone()
            uradjene.invoke(result!!)
        }
    }

    fun getBuduce(ankete : (anketa : List<Anketa>) -> Unit){
        scope.launch {
            val result = AnketaRepository.getFuture()
            ankete.invoke(result!!)

        }
    }

    fun getProsle(ankete : (anketa : List<Anketa>) -> Unit){
        scope.launch {
            val result = AnketaRepository.getPast()
            ankete.invoke(result!!)
        }

    }



}
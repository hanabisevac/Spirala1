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
            else result = AnketaRepository.dajSveDb() as MutableList<Anketa>

            sveAnkete.invoke(result)
        }
    }

    fun getUpisane(upisane : (ankete : List<Anketa>) -> Unit) {
        scope.launch {
            var result = mutableListOf<Anketa>()
            if(KonekcijaRepository.getKonekcija()) result = AnketaRepository.getUpisane() as MutableList<Anketa>
            else result = AnketaRepository.getMyAnketeBaza() as MutableList<Anketa>
            upisane.invoke(result)
        }
    }

    fun getUradjene(uradjene : (ankete: List<Anketa>) -> Unit) {
        scope.launch {
            var result = mutableListOf<Anketa>()
            if(KonekcijaRepository.getKonekcija()) result = AnketaRepository.getDone() as MutableList<Anketa>
            else result = AnketaRepository.getDoneBaza() as MutableList<Anketa>
            uradjene.invoke(result)
        }
    }

    fun getBuduce(ankete : (anketa : List<Anketa>) -> Unit){
        scope.launch {
            var result = mutableListOf<Anketa>()
            if(KonekcijaRepository.getKonekcija()) result = AnketaRepository.getFuture() as MutableList<Anketa>
            else result = AnketaRepository.getFutureBaza() as MutableList<Anketa>
            ankete.invoke(result)

        }
    }

    fun getProsle(ankete : (anketa : List<Anketa>) -> Unit){
        scope.launch {
            var result = mutableListOf<Anketa>()
            if(KonekcijaRepository.getKonekcija()) result = AnketaRepository.getPast() as MutableList<Anketa>
            else result = AnketaRepository.getPastBaza() as MutableList<Anketa>
            ankete.invoke(result)
        }

    }



}
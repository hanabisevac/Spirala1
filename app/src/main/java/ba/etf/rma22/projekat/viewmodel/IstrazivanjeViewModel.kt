package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class IstrazivanjeViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getIstrazivanja(svaIstrazivanja : (istrazivanje : List<Istrazivanje>)->Unit) {
        scope.launch {
            val lista = mutableListOf<Istrazivanje>()
            lista.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(1))
            lista.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(2))
            svaIstrazivanja.invoke(lista)
        }
    }

    fun getIstrazivanjePoGodini(g : Int, istrazivanjePoGodini: (istrazivanja : List<Istrazivanje>)->Unit){
        scope.launch {
            val sva = mutableListOf<Istrazivanje>()
            sva.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(1))
            sva.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(2))
            val lista = mutableListOf<Istrazivanje>()
            for(i in sva.indices){
                if(sva[i].godina == g) lista.add(sva[i])
            }
            istrazivanjePoGodini.invoke(lista)
        }
    }

    fun getSlobodnaIstrazivanja(sva : List<Istrazivanje>, slobodnaIstrazivanja : (istrazivanje : List<Istrazivanje>) -> Unit){
        scope.launch {
            val lista = mutableListOf<Istrazivanje>()
            val grupe = IstrazivanjeIGrupaRepository.getUpisaneGrupe()
            var ima : Boolean = false
            for(i in sva.indices){
                for(j in grupe.indices){
                    if(grupe[j].istrazivanjeId == sva[i].id) ima=true
                }
                if(!ima){
                    lista.add(sva[i])
                }
                else ima = false
            }
            slobodnaIstrazivanja.invoke(lista)
        }
    }

    /*fun getIstrazivanjePoGodini(g : Int) : List<Istrazivanje>{
        return IstrazivanjeRepository.getIstrazivanjeByGodina(g)
    }

    fun getMojaIstrazivanja() : List<Istrazivanje> {
        return IstrazivanjeRepository.getUpisani()
    }*/
}

package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GrupeViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun upisiStudenta(poruka : (p : String) -> Unit) {
        scope.launch {
            val result = GrupaRepository.dodajStudenta(1, AccountRepository.acHash)
            poruka.invoke(result.poruka)
        }
    }

    fun getGrupeByIstrazivanje(str : String, getGrupeZaIstrazivanja : (grupe : List<Grupa>) -> Unit){
        scope.launch {
            val istrazivanja = mutableListOf<Istrazivanje>()
            istrazivanja.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(1))
            istrazivanja.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(2))
            val grupeZadane = mutableListOf<Grupa>()
            for(i in istrazivanja.indices){
                if(istrazivanja[i].naziv == str) grupeZadane.addAll(IstrazivanjeIGrupaRepository.getGrupeZaIstrazivanje(istrazivanja[i].id))
            }
            getGrupeZaIstrazivanja.invoke(grupeZadane)
        }
    }

    fun getSlobodneGrupe(sve : List<Grupa>, nisuStudentoveGrupe : (grupe : List<Grupa>) -> Unit){
        scope.launch {
            val grupe = IstrazivanjeIGrupaRepository.getUpisaneGrupe()
            val slobodne = mutableListOf<Grupa>()
            var ima : Boolean = false
            for(i in sve.indices){
                for(j in grupe.indices){
                    if(sve[i] == grupe[j]) ima = true
                }
                if(!ima){
                    slobodne.add(sve[i])
                }
                else ima=false
            }

            nisuStudentoveGrupe.invoke(slobodne)

        }
    }

    /*fun getGrupeByIstrazivanje(str : String) : List<Grupa> {
        return GrupaRepository.getGroupsByIstrazivanje(str)
    }
    fun getSveGrupe() : List<Grupa> {
        return GrupaRepository.getAll()
    }*/
}

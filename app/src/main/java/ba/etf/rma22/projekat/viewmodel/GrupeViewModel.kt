package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GrupeViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

   /* fun upisiStudenta(poruka : (p : String) -> Unit) {
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
    }*/

    fun getSlobodneGrupe(nisuStudentoveGrupe : (grupe : List<Grupa>) -> Unit){
        scope.launch {
            var slobodne = mutableListOf<Grupa>()
            if(KonekcijaRepository.getKonekcija()){
                val grupe = IstrazivanjeIGrupaRepository.getUpisaneGrupe()
                val sve = IstrazivanjeIGrupaRepository.getGrupe()
                var ima : Boolean = false
                for(i in sve!!.indices){
                    for(j in grupe!!.indices){
                        if(sve[i].id == grupe[j].id) {
                            ima = true
                            break
                        }
                    }
                    if(!ima){
                        slobodne.add(sve[i])
                    }
                    else ima=false
                }
            }
            else slobodne = AppDatabase.getInstance(ContextRepo.getContext()).grupaDAO().getAllGrupe() as MutableList<Grupa>


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

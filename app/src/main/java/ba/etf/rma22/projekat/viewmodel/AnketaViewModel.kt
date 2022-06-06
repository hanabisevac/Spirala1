package ba.etf.rma22.projekat.viewmodel


import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import ba.etf.rma22.projekat.data.repositories.TakeAnketaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnketaViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getAll(onSuccess: (ankete: List<Anketa>) -> Unit)  {
        scope.launch {
            val result = mutableListOf<Anketa>()
            result.addAll(AnketaRepository.getAll(1))
            result.addAll(AnketaRepository.getAll(2))
            val grupe = IstrazivanjeIGrupaRepository.getGrupe()
            val istrazivanja = mutableListOf<Istrazivanje>()
            val pocete = TakeAnketaRepository.getPoceteAnkete()
            istrazivanja.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(1))
            istrazivanja.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(2))

            for(i in istrazivanja.indices){
                val g = IstrazivanjeIGrupaRepository.getGrupeZaIstrazivanje(istrazivanja[i].id)
                for(j in g.indices){
                    for(k in grupe.indices){
                        if(g[j] == grupe[k]) grupe[k].nazivIstrazivanja = istrazivanja[i].naziv
                    }
                }
            }
            for(i in grupe.indices){
                val a = AnketaRepository.getDostupneAnketeZaGrupu(grupe[i].id)
                for(j in a.indices){
                    for(k in result.indices){
                        if(result[k] == a[j]){
                            result[k].nazivGrupe = grupe[i].naziv
                            result[k].nazivIstrazivanja = grupe[i].nazivIstrazivanja
                        }
                    }
                }
            }
            result.stream().forEach { a -> a.progres = 0 }
            if(pocete!=null){
                for(i in result.indices){
                    for(j in pocete.indices){
                        if(pocete[j].AnketumId == result[i].id) {
                            result[i].progres = pocete[j].progres
                            break
                        }
                    }
                }

            }
            onSuccess.invoke(result)
        }
    }

    fun getUpisane(upisane : (ankete : List<Anketa>) -> Unit) {
        scope.launch {
            val result = AnketaRepository.getUpisane()
            val grupe = IstrazivanjeIGrupaRepository.getGrupe()
            val istrazivanja = mutableListOf<Istrazivanje>()
            val pocete = TakeAnketaRepository.getPoceteAnkete()
            istrazivanja.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(1))
            istrazivanja.addAll(IstrazivanjeIGrupaRepository.getIstrazivanja(2))

            for(i in istrazivanja.indices){
                val g = IstrazivanjeIGrupaRepository.getGrupeZaIstrazivanje(istrazivanja[i].id)
                for(j in g.indices){
                    for(k in grupe.indices){
                        if(g[j] == grupe[k]) grupe[k].nazivIstrazivanja = istrazivanja[i].naziv
                    }
                }
            }
            for(i in grupe.indices){
                val a = AnketaRepository.getDostupneAnketeZaGrupu(grupe[i].id)
                for(j in a.indices){
                    for(k in result.indices){
                        if(result[k] == a[j]){
                            result[k].nazivGrupe = grupe[i].naziv
                            result[k].nazivIstrazivanja = grupe[i].nazivIstrazivanja
                        }
                    }
                }
            }
            result.stream().forEach { a -> a.progres = 0 }
            if(pocete!=null){
                for(i in result.indices){
                    for(j in pocete.indices){
                        if(pocete[j].AnketumId == result[i].id) {
                            result[i].progres = pocete[j].progres
                            break
                        }
                    }
                }

            }
            upisane.invoke(result)
        }
    }

    fun getZavrsene(zavrsene : (ankete: List<Anketa>) -> Unit) {
        scope.launch {



        }
    }



    /*fun getSveAnkete() : List<Anketa>{
        val lista : MutableList<Anketa> = AnketaRepository.getAll() as MutableList<Anketa>
        lista.sort()
        return lista
    }


    fun getMyAnkete() : List<Anketa> {
        val lista : MutableList<Anketa> = AnketaRepository.getMyAnkete() as MutableList<Anketa>
        lista.sort()
        return lista
    }

    fun getUradjeneAnkete() : List<Anketa>{
        val lista : MutableList<Anketa> = AnketaRepository.getDone() as MutableList<Anketa>
        lista.sort()
        return lista
    }

    fun getAktivneAnkete() : List<Anketa> {
        val lista : MutableList<Anketa> = AnketaRepository.aktivneAnkete() as MutableList<Anketa>
        lista.sort()
        return lista

    }

    fun getAktvineIBuduce() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        lista.addAll(getAktivneAnkete())
        lista.addAll(getSljedeceAnkete())
        lista.sort()
        return lista
    }


    fun getZavrseneAnkete() : List<Anketa> {
        val lista : MutableList<Anketa> = AnketaRepository.getNotTaken() as MutableList<Anketa>
        lista.sort()
        return lista


    }
    fun getSljedeceAnkete() : List<Anketa>{
        val lista : MutableList<Anketa> = AnketaRepository.getFuture() as MutableList<Anketa>
        lista.sort()
        return lista

    }

    fun getAnketu(naziv : String, istrazivanje : String) : Anketa? {
        return AnketaRepository.getAnketu(naziv, istrazivanje)
    }
*/

}
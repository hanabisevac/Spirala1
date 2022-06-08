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
import java.util.*

class AnketaViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)


    fun getAll(onSuccess: (ankete: List<Anketa>) -> Unit)  {
        scope.launch {
            val lista = AnketaRepository.getAll()
            val result = AnketaRepository.dodajPodatkeAnkete(lista)
            onSuccess.invoke(result)
        }
    }

    fun dajSve(sveAnkete : (ankete : List<Anketa>) -> Unit){
        scope.launch {
            val grupe = IstrazivanjeIGrupaRepository.getGrupe()
            val lista = mutableListOf<Anketa>()
            for(i in grupe.indices){
                val a = AnketaRepository.getDostupneAnketeZaGrupu(grupe[i].id)
                val istrazivanje = IstrazivanjeIGrupaRepository.getIstrazivanjeZaGrupe(grupe[i].id)
                for(j in a.indices){
                    a[j].nazivGrupe = grupe[i].naziv
                    a[j].nazivIstrazivanja = istrazivanje!!.naziv
                }
                lista.addAll(a)
            }
            lista.stream().forEach { a -> a.progres = 0 }
            val zapocete = TakeAnketaRepository.getPoceteAnkete()
            if(zapocete != null){
                for(i in lista.indices){
                    for(j in zapocete.indices){
                        if(zapocete[j].AnketumId == lista[i].id){
                            lista[i].progres = zapocete[j].progres
                            if(lista[i].progres == 100) lista[i].datumRada = zapocete[j].datumRada
                        }
                    }
                }
            }
            sveAnkete.invoke(lista)
        }
    }

    fun getUpisane(upisane : (ankete : List<Anketa>) -> Unit) {
        scope.launch {
            val grupe = IstrazivanjeIGrupaRepository.getUpisaneGrupe()
            val result = mutableListOf<Anketa>()
            for(i in grupe.indices){
                val a = AnketaRepository.getDostupneAnketeZaGrupu(grupe[i].id)
                for(j in a.indices){
                    a[j].nazivGrupe = grupe[i].naziv
                    a[j].nazivIstrazivanja = IstrazivanjeIGrupaRepository.getIstrazivanjeZaGrupe(grupe[i].id)!!.naziv
                }
                result.addAll(a)
            }
            result.stream().forEach { a -> a.progres = 0 }
            val pocete = TakeAnketaRepository.getPoceteAnkete()
            if(pocete != null){
                for(i in result.indices){
                    for(j in pocete.indices){
                        if(result[i].id == pocete[j].AnketumId){
                            result[i].progres = pocete[j].progres
                            if(result[i].progres == 100) result[i].datumRada = pocete[j].datumRada
                            break
                        }
                    }
                }
            }


            upisane.invoke(result)
        }
    }

    fun getUradjene(uradjene : (ankete: List<Anketa>) -> Unit) {
        scope.launch {
            val grupe = IstrazivanjeIGrupaRepository.getUpisaneGrupe()
            val result = mutableListOf<Anketa>()
            for(i in grupe.indices){
                val a = AnketaRepository.getDostupneAnketeZaGrupu(grupe[i].id)
                for(j in a.indices){
                    a[j].nazivGrupe = grupe[i].naziv
                    a[j].nazivIstrazivanja = IstrazivanjeIGrupaRepository.getIstrazivanjeZaGrupe(grupe[i].id)!!.naziv
                }
                result.addAll(a)
            }
            result.stream().forEach { a -> a.progres = 0 }
            val pocete = TakeAnketaRepository.getPoceteAnkete()
            if(pocete != null){
                for(i in result.indices){
                    for(j in pocete.indices){
                        if(result[i].id == pocete[j].AnketumId){
                            result[i].progres = pocete[j].progres
                            if(result[i].progres == 100) result[i].datumRada = pocete[j].datumRada
                            break
                        }
                    }
                }
            }
            val lista = mutableListOf<Anketa>()
           for(i in result.indices){
               if(result[i].datumRada != null) lista.add(result[i])
           }
            //var listica = lista.toSet().toList()
            uradjene.invoke(lista)
        }
    }

    fun getBuduce(ankete : (anketa : List<Anketa>) -> Unit){
        scope.launch {
            val grupe = IstrazivanjeIGrupaRepository.getUpisaneGrupe()
            val result = mutableListOf<Anketa>()
            for(i in grupe.indices){
                val a = AnketaRepository.getDostupneAnketeZaGrupu(grupe[i].id)
                for(j in a.indices){
                    a[j].nazivGrupe = grupe[i].naziv
                    a[j].nazivIstrazivanja = IstrazivanjeIGrupaRepository.getIstrazivanjeZaGrupe(grupe[i].id)!!.naziv
                }
                result.addAll(a)
            }
            result.stream().forEach { a -> a.progres = 0 }
            val pocete = TakeAnketaRepository.getPoceteAnkete()
            if(pocete != null){
                for(i in result.indices){
                    for(j in pocete.indices){
                        if(result[i].id == pocete[j].AnketumId){
                            result[i].progres = pocete[j].progres
                            if(result[i].progres == 100) result[i].datumRada = pocete[j].datumRada
                            break
                        }
                    }
                }
            }
            val lista = mutableListOf<Anketa>()
            for(i in result.indices){
                if(result[i].datumRada == null && (result[i].datumKraj == null || result[i].datumKraj!!.after(Date()))){
                    if(result[i].datumPocetak.after(Date())) lista.add(result[i])
                    else if(result[i].datumPocetak.before(Date())) lista.add(result[i])
                }

            }
            ankete.invoke(lista)

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
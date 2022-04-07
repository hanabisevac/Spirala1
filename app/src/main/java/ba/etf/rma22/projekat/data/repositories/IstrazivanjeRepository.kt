package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.Korisnik.dajKorisnika
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import com.example.spirala1.data.listaIstrazivanja

object IstrazivanjeRepository {

    fun getIstrazivanjeByGodina(godina : Int) : List<Istrazivanje>{
        val nova = mutableListOf<Istrazivanje>()
        val lista : List<Istrazivanje> = listaIstrazivanja()
        for(i in 0..lista.size-1){
            if(lista[i].godina == godina) nova.add(lista[i])
        }
        return nova
    }

    fun getAll() : List<Istrazivanje> {
        return listaIstrazivanja()
    }

    fun getUpisani() : List<Istrazivanje> {
        val imena = dajKorisnika().listaAnketa
        val lista = dajListu(imena)
        val nova = mutableListOf<Istrazivanje>()
        val sve = getAll()
        for(i in 0..sve.size-1){
            for(j in 0..lista.size-1){
                if(sve[i].naziv == lista[j]) nova.add(sve[i])
            }
        }
        return nova
    }

    fun dajListu(imena : List<Anketa>) : List<String>{
        val lista = mutableListOf<String>()
        for(i in 0..imena.size-1){
            lista.add(imena[i].nazivIstrazivanja)
        }
        return lista
    }

}
package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.staticdata.dajListuPitanja

object PitanjaRepository {

    fun dajSvaPitanja() : List<Pitanje> {
        return dajListuPitanja()
    }

    fun dajPitanjaZaAnketu(naziv : String) : List<Pitanje> {
        val lista = dajSvaPitanja()
        val novaLista = mutableListOf<Pitanje>()
        for(i in lista.indices){
            if(lista[i].naziv==naziv) novaLista.add(lista[i])
        }
        return novaLista
    }

}
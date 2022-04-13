package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.staticdata.listaGrupa

object GrupaRepository {

    fun getGroupsByIstrazivanje(nazivIstrazivanja : String) : List<Grupa> {
        val lista = listaGrupa()
        val nova = mutableListOf<Grupa>()
        for(i in lista.indices){
            if(lista[i].nazivIstrazivanja == nazivIstrazivanja) nova.add(lista[i])
        }
        return nova
    }

    fun getAll() : List<Grupa> {
        return listaGrupa()
    }
}
package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
import com.example.spirala1.data.listaGrupa

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
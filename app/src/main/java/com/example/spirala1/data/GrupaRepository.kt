package com.example.spirala1.data

object GrupaRepository {

    fun getGroupByIstrazivanje(nazivIstrazivanja : String) : List<Grupa> {
        val lista = listaGrupa()
        val nova = mutableListOf<Grupa>()
        for(i in 0..lista.size-1){
            if(lista[i].nazivIstrazivanja == nazivIstrazivanja) nova.add(lista[i])
        }
        return nova
    }
}
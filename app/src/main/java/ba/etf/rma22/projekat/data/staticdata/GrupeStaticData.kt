package com.example.spirala1.data

import ba.etf.rma22.projekat.data.models.Grupa



fun listaGrupa() : List<Grupa> {
    return spojiGrupe()
}

fun spojiGrupe() : List<Grupa> {
    val ankete = listaAnketa()
    val lista = mutableListOf<Grupa>()
    for(i in 0..ankete.size-1){
        lista.add(Grupa(ankete[i].nazivGrupe, ankete[i].nazivIstrazivanja))
    }
    return lista
}

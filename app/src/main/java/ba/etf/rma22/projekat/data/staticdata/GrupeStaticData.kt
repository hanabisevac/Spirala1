package com.example.spirala1.data

import ba.etf.rma22.projekat.data.models.Grupa

/*
Grupa 1 - Istrazivanje 3 1g
Grupa 2 - Istrazivanje 1 5g
Grupa 3 - Istrazivanje 2 2g
Grupa 4 - Istrazivanje 4 4g
Grupa 5 - Istrazivanje 2 2g
Grupa 6 - Istrazivanje 5 5g
Grupa 7 - Istrazivanje 3 1g
Grupa 8 - Istrazivanje 4 4g
Grupa 9 - Istrazivanje 5 5g
*/

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

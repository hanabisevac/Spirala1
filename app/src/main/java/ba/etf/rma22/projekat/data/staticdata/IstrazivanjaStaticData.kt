package com.example.spirala1.data

import ba.etf.rma22.projekat.data.models.Istrazivanje

fun listaIstrazivanja() : List<Istrazivanje> {
    return listOf(
        Istrazivanje("Istrazivanje 1", 5),
        Istrazivanje("Istrazivanje 2", 2),
        Istrazivanje("Istrazivanje 3", 1),
        Istrazivanje("Istrazivanje 4", 4),
        Istrazivanje("Istrazivanje 5", 3),
        Istrazivanje("Istrazivanje 6", 1),
        Istrazivanje("Istrazivanje 7", 3),
        Istrazivanje("Istrazivanje 8", 5)
    )
}
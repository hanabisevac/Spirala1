package com.example.spirala1.data

import ba.etf.rma22.projekat.data.models.Istrazivanje

fun listaIstrazivanja() : List<Istrazivanje> {
    return listOf(
        Istrazivanje("Istrazivanje o kvaliteti nastave", 1),
        Istrazivanje("Istrazivanje javnog mnjenja", 2),
        Istrazivanje("Drustveno istrazivanje", 1),
        Istrazivanje("Istrazivanje u svrhu zastite zivotinja", 4),
        Istrazivanje("Istrazivanje o zivotinjama", 3),
        Istrazivanje("Istrazivanje o cyberbullying-u", 2),
        Istrazivanje("Istrazivanje o studentskim preferencijama", 3),
        Istrazivanje("Istrazivanje o zastupljenosti drustvenih mreza", 1),
        Istrazivanje("Istrazivanje o uticaju medija", 5)
    )
}
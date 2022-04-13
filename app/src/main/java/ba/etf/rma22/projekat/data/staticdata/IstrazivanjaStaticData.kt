package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Istrazivanje

fun listaIstrazivanja() : List<Istrazivanje> {
    return listOf(
        Istrazivanje("Istrazivanje o kvaliteti nastave", 1),
        Istrazivanje("Istrazivanje javnog mnjenja", 2),
        Istrazivanje("Drustveno istrazivanje", 4),
        Istrazivanje("Istrazivanje o zivotinjama", 3),
        Istrazivanje("Istrazivanje o cyberbullying-u", 2),
        Istrazivanje("Istrazivanje o uticaju medija", 5)
    )
}


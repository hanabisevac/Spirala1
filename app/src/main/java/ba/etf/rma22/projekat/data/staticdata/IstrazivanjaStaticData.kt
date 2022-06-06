package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Istrazivanje

fun listaIstrazivanja() : List<Istrazivanje> {
    return listOf(
        Istrazivanje(1, "Istrazivanje 1", 1),
        Istrazivanje(2, "Istrazivanje 2", 2),
        Istrazivanje(3, "Istrazivanje 3", 4),
        Istrazivanje(4, "Istrazivanje 4", 3),
        Istrazivanje(5, "Istrazivanje 5", 2),
        Istrazivanje(6, "Istrazivanje 6", 5)
    )
}


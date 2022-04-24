package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Istrazivanje

fun listaIstrazivanja() : List<Istrazivanje> {
    return listOf(
        Istrazivanje("Istrazivanje 1", 1),
        Istrazivanje("Istrazivanje 2", 2),
        Istrazivanje("Istrazivanje 3", 4),
        Istrazivanje("Istrazivanje 4", 3),
        Istrazivanje("Istrazivanje 5", 2),
        Istrazivanje("Istrazivanje 6", 5)
    )
}


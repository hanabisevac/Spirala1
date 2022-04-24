package ba.etf.rma22.projekat.view

import ba.etf.rma22.projekat.data.models.PitanjeAnketa

val pa1 = PitanjeAnketa("Pitanje 1", "Anketa 2", "Istrazivanje 1", null)
val pa2 = PitanjeAnketa("Pitanje 2", "Anketa 2", "Istrazivanje 1", null)
val pa3 = PitanjeAnketa("Pitanje 3", "Anketa 2", "Istrazivanje 1", null)
val pa4 = PitanjeAnketa("Pitanje 4", "Anketa 2", "Istrazivanje 1", null)

fun dajSvaPitanjaAnketa() : List<PitanjeAnketa> {
    return listOf(pa1, pa2, pa3, pa4)
}
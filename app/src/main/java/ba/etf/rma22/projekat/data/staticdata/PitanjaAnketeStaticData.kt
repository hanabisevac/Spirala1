package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.PitanjeAnketa

val pa1 = PitanjeAnketa("Pitanje 1", "Anketa 2", "Istrazivanje 1", null)
val pa2 = PitanjeAnketa("Pitanje 2", "Anketa 2", "Istrazivanje 1", null)
val pa3 = PitanjeAnketa("Pitanje 3", "Anketa 2", "Istrazivanje 1", null)
val pa4 = PitanjeAnketa("Pitanje 4", "Anketa 2", "Istrazivanje 1", null)

val pa5 = PitanjeAnketa("Pitanje 5", "Anketa 3", "Istrazivanje 2", null)
val pa6 = PitanjeAnketa("Pitanje 6", "Anketa 3", "Istrazivanje 2", null)
val pa7 = PitanjeAnketa("Pitanje 7", "Anketa 3", "Istrazivanje 2", null)
val pa8 = PitanjeAnketa("Pitanje 8", "Anketa 3", "Istrazivanje 2", null)

fun dajSvaPitanjaAnketa() : List<PitanjeAnketa> {
    return listOf(pa1, pa2, pa3, pa4, pa5, pa6, pa7, pa8)
}
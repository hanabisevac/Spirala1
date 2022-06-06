package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Grupa




val g1 = Grupa(1, "Grupa 1", "Istrazivanje 1", 1)
val g2 = Grupa(2, "Grupa 2", "Istrazivanje 1", 1)
val g3 = Grupa(3, "Grupa 3", "Istrazivanje 2", 2)
val g4 = Grupa(1, "Grupa 1", "Istrazivanje 3", 3)
val g5 = Grupa(3, "Grupa 3", "Istrazivanje 4", 4)
val g6 = Grupa(2, "Grupa 2", "Istrazivanje 3", 3)
val g7 = Grupa(4, "Grupa 4", "Istrazivanje 2", 2)
val g8 = Grupa(5, "Grupa 5", "Istrazivanje 4", 4)
val g9 = Grupa(6, "Grupa 6", "Istrazivanje 5", 5)
val g10 = Grupa(4, "Grupa 4", "Istrazivanje 6", 6)

fun listaGrupa() : List<Grupa> {
    return listOf(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10)
}


/*fun spojiGrupe() : List<Grupa> {
    val ankete = listaAnketa()
    val lista = mutableListOf<Grupa>()
    for(i in 0..ankete.size-1){
        lista.add(Grupa(ankete[i].nazivGrupe, ankete[i].nazivIstrazivanja))
    }
    return lista
}*/

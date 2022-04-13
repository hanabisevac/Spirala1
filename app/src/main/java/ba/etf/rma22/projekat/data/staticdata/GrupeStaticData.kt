package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Grupa



val g1 = Grupa("Grupa 1", "Istrazivanje o cyberbullying-u")
val g2 = Grupa("Grupa 2", "Istrazivanje o cyberbullying-u")
val g3 = Grupa("Grupa 3", "Istrazivanje o cyberbullying-u")
val g4 = Grupa("Grupa 1", "Istrazivanje o kvaliteti nastave")
val g5 = Grupa("Grupa 2", "Istrazivanje o kvaliteti nastave")
val g6 = Grupa("Grupa 4", "Drustveno istrazivanje")
val g7 = Grupa("Grupa 3", "Drustveno istrazivanje")
val g8 = Grupa("Grupa 5", "Istrazivanje o zivotinjama")
val g9 = Grupa("Grupa 6", "Istrazivanje o zivotinjama")
val g10 = Grupa("Grupa 2", "Istrazivanje o zivotinjama")
val g11 = Grupa("Grupa 7", "Istrazivanje o uticaju medija")
val g12 = Grupa("Grupa 8", "Istrazivanje o uticaju medija")
val g13 = Grupa("Grupa 7", "Istrazivanje javnog mnjenja")
val g14 = Grupa("Grupa 5", "Istrazivanje javnog mnjenja")

fun listaGrupa() : List<Grupa> {
    return listOf(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14)
}


/*
fun spojiGrupe() : List<Grupa> {
    val ankete = listaAnketa()
    val lista = mutableListOf<Grupa>()
    for(i in 0..ankete.size-1){
        lista.add(Grupa(ankete[i].nazivGrupe, ankete[i].nazivIstrazivanja))
    }
    return lista
}
*/

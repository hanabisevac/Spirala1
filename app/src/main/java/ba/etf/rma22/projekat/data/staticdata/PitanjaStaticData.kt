package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Pitanje

val p1 = Pitanje("Pitanje 1", "Najdraza zivotinja", listOf("Macka", "Pas"))
val p2 = Pitanje("Pitanje 2", "Imate li kucnog ljubimca?", listOf("Da", "Ne"))
val p3 = Pitanje("Pitanje 3", "Koliko ljubimaca imate?", listOf("0", "1", "Vise od 1"))
val p4 = Pitanje("Pitanje 4", "Da li bi ste usvojili tigra?", listOf("Da", "Ne"))
val p5 = Pitanje("Pitanje 5", "Najdraza zivotinja", listOf("Macka", "Pas"))
val p6 = Pitanje("Pitanje 6", "Imate li kucnog ljubimca?", listOf("Da", "Ne"))
val p7 = Pitanje("Pitanje 7", "Koliko ljubimaca imate?", listOf("0", "1", "Vise od 1"))
val p8 = Pitanje("Pitanje 8", "Da li bi ste usvojili tigra?", listOf("Da", "Ne"))


fun dajListuPitanja() : List<Pitanje> {
    return listOf(p1, p2, p3, p4, p5, p6, p7, p8)
}
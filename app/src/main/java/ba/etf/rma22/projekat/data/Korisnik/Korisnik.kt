package ba.etf.rma22.projekat.data.Korisnik

import ba.etf.rma22.projekat.data.ankete.Anketa

class Korisnik(
    val ime : String,
    val istrazivanja : List<String>,
    val listaAnketa : List<Anketa>
) {
}
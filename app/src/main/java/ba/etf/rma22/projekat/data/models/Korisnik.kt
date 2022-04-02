package ba.etf.rma22.projekat.data.models

import ba.etf.rma22.projekat.data.models.Anketa

class Korisnik(
    val ime : String,
    val istrazivanja : List<String>,
    val listaAnketa : List<Anketa>
) {
}
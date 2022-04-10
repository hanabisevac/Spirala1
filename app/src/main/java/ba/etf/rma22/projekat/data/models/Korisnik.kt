package ba.etf.rma22.projekat.data.models

import ba.etf.rma22.projekat.data.models.Anketa

data class Korisnik(
    val ime : String,
    val listaGrupa : MutableList<Grupa>
) {



}
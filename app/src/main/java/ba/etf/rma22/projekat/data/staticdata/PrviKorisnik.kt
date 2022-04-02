package ba.etf.rma22.projekat.data.Korisnik

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Korisnik
import com.example.spirala1.data.*

fun dajKorisnika() : Korisnik {
    return Korisnik("Hana", listaZaIstrazivanja(), listaZaAnkete())
}

fun listaZaIstrazivanja() : List<String> {
    return listOf<String>(
        "Istrazivanje 1",
        "Istrazivanje 2",
        "Istrazivanje 7"
    )
}

fun listaZaAnkete() : List<Anketa>{
    return listOf(
        anketa1, anketa8, anketa3, anketa9
    )
}

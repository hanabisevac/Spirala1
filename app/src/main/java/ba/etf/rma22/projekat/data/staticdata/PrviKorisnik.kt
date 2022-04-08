package ba.etf.rma22.projekat.data.Korisnik

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Korisnik
import com.example.spirala1.data.*

fun dajKorisnika() : Korisnik {
    return Korisnik("Hana", listaZaAnkete())
}



fun listaZaAnkete() : MutableList<Anketa>{
    return mutableListOf(
        anketa4, anketa14, anketa10, anketa15
    )
}

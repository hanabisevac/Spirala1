package ba.etf.rma22.projekat.data.Korisnik

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Korisnik
import com.example.spirala1.data.*

fun dajKorisnika() : Korisnik {
    return Korisnik("Hana", listaZaGrupe())
}



fun listaZaGrupe() : MutableList<Grupa>{
    return mutableListOf(
        g3
    )
}


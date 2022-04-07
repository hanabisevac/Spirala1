package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.Korisnik.dajKorisnika
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Korisnik

class KorisnikRepository {
    companion object {
        var korisnik : Korisnik
        init {
            korisnik = dajKorisnika()
        }

        fun getAnkete() : List<Anketa>{
            return korisnik.listaAnketa
        }

        fun addAnkete(anketa : Anketa) {
            korisnik.listaAnketa.add(anketa)
        }

    }

}
package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.staticdata.dajKorisnika
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Korisnik

class KorisnikRepository {
    companion object {
        var korisnik : Korisnik = dajKorisnika()

        fun getGrupe() : List<Grupa>{
            return korisnik.listaGrupa
        }

        fun addGrupu(grupa : Grupa) {
            korisnik.listaGrupa.add(grupa)
        }

       /* fun getAnketeZaGrupe(): List<Anketa> {
            return korisnik.listaAnketaZaGrupe
        }
        fun addAnketaZaGrupe(anketa : Anketa) {
            korisnik.listaAnketaZaGrupe.add(anketa)
        }*/

    }

}
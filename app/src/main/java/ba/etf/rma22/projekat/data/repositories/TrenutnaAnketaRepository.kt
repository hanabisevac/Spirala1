package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import java.util.*

class TrenutnaAnketaRepository {

    companion object{
        private lateinit var anketaPamti : Anketa
        private var brojPitanja : Int = 0

        fun postaviAnketu(anketa : Anketa) {
            anketaPamti = anketa
        }

        fun dajAnketu() : Anketa {
            return anketaPamti
        }
        fun postaviProgres(prog : Float) {
            anketaPamti.setProgress(prog)
        }
        fun postaviDatum(datum : Date){
            anketaPamti.setDate(datum)
        }

        fun postaviBrojPitanja(broj : Int) {
            this.brojPitanja = broj
        }

        fun dajBrojPitanja() : Int{
            return this.brojPitanja
        }
    }

}
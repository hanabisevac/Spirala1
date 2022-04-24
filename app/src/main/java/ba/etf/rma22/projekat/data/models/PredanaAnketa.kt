package ba.etf.rma22.projekat.data.models

import java.util.*

class PredanaAnketa {

    companion object{
        lateinit var anketaPamti : Anketa

        fun postaviAnketu(anketa : Anketa) {
            anketaPamti = anketa
        }

        fun dajAnketu() : Anketa{
            return anketaPamti
        }
        fun postaviProgres(prog : Float) {
            anketaPamti.setProgress(prog)
        }
        fun postaviDatum(datum : Date){
            anketaPamti.setDate(datum)
        }
    }

}
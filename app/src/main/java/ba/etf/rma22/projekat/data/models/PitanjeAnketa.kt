package ba.etf.rma22.projekat.data.models

//naziv je naziv pitanja
//anketa je naziv ankete

class PitanjeAnketa(val naziv : String, val anketa : String, val istrazivanje : String,  var odgovor : String?) {

    fun postaviOdgovor(odg : String){
        this.odgovor = odg
    }

    fun dajOdgovor() : String? {
        return this.odgovor
    }
}
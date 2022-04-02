package ba.etf.rma22.projekat.data.ankete

import java.util.*

class Anketa(val naziv : String,
             val nazivIstrazivanja : String,
             val datumPocetka : Date,
             val datumKraj : Date,
             val datumRada : Date ?,
             val trajanje : Int,
             val nazivGrupe : String,
             val progres : Float) : Comparable<Anketa> {

    override fun compareTo(other: Anketa): Int {
        val cmp = this.datumPocetka.compareTo(other.datumPocetka)
        if(cmp<0) return -1
        return 1
    }

    fun getStatus() : String{
        if(datumRada != null) return "plava"
        else if(datumPocetka.before(Date()) && datumKraj.after(Date())) return "zelena"
        else if(datumKraj.before(Date())) return "crvena"
        return "zuta"
    }
}
package ba.etf.rma22.projekat.data.models

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Anketa

        if (naziv != other.naziv) return false
        if (nazivIstrazivanja != other.nazivIstrazivanja) return false
        if (datumPocetka != other.datumPocetka) return false
        if (datumKraj != other.datumKraj) return false
        if (datumRada != other.datumRada) return false
        if (trajanje != other.trajanje) return false
        if (nazivGrupe != other.nazivGrupe) return false
        if (progres != other.progres) return false

        return true
    }

    override fun hashCode(): Int {
        var result = naziv.hashCode()
        result = 31 * result + nazivIstrazivanja.hashCode()
        result = 31 * result + datumPocetka.hashCode()
        result = 31 * result + datumKraj.hashCode()
        result = 31 * result + (datumRada?.hashCode() ?: 0)
        result = 31 * result + trajanje
        result = 31 * result + nazivGrupe.hashCode()
        result = 31 * result + progres.hashCode()
        return result
    }
}
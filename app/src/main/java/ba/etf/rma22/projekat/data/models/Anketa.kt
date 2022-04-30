package ba.etf.rma22.projekat.data.models

import java.util.*

data class Anketa(val naziv : String,
             val nazivIstrazivanja : String,
             val datumPocetak : Date,
             val datumKraj : Date,
             var datumRada : Date ?,
             val trajanje : Int,
             val nazivGrupe : String,
             var progres : Float) : Comparable<Anketa> {

    override fun compareTo(other: Anketa): Int {
        val cmp = this.datumPocetak.compareTo(other.datumPocetak)
        if(cmp<0) return -1
        return 1
    }

    fun setProgress(progres : Float){
        this.progres += progres
    }

    fun setDate(date : Date) {
        this.datumRada = date
    }


    fun getStatus() : String{
        if(datumRada != null) return "plava"
        else if(datumPocetak.before(Date()) && datumKraj.after(Date())) return "zelena"
        else if(datumKraj.before(Date())) return "crvena"
        return "zuta"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Anketa

        if (naziv != other.naziv) return false
        if (nazivIstrazivanja != other.nazivIstrazivanja) return false
        if (datumPocetak != other.datumPocetak) return false
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
        result = 31 * result + datumPocetak.hashCode()
        result = 31 * result + datumKraj.hashCode()
        result = 31 * result + (datumRada?.hashCode() ?: 0)
        result = 31 * result + trajanje
        result = 31 * result + nazivGrupe.hashCode()
        result = 31 * result + progres.hashCode()
        return result
    }
}
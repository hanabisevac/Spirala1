package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Entity
data class Anketa(
    @PrimaryKey @SerializedName("id") val id : Int,
    @ColumnInfo(name ="naziv") @SerializedName("naziv") val naziv : String,
    @ColumnInfo(name = "nazivIstrazivanja") var nazivIstrazivanja : String ?,
    @ColumnInfo(name = "datumPocetak") @SerializedName("datumPocetak") val datumPocetak : String,
    @ColumnInfo(name = "datumKraj") @SerializedName("datumKraj") val datumKraj : String?,
    @ColumnInfo(name = "datumRada") var datumRada : String ?,
    @ColumnInfo(name = "trajanje") @SerializedName("trajanje") val trajanje : Int,
    @ColumnInfo(name = "nazivGrupe") var nazivGrupe : String ?,
    @ColumnInfo(name = "progres") var progres : Int ?) : Comparable<Anketa> {

    override fun compareTo(other: Anketa): Int {
        val cmp = this.datumPocetak.compareTo(other.datumPocetak)
        if(cmp<0) return -1
        return 1
    }

    fun setProgress(progres : Int){
        this.progres = progres
    }


    fun dajDatumPocetak() : Date{
        val formatter = SimpleDateFormat("yyyy-mm-dd")
        val dPocetak = formatter.parse(datumPocetak)
        return dPocetak
    }

    fun dajDatumKraj() : Date ?{
        val formatter = SimpleDateFormat("yyyy-mm-dd")
        var dKraj : Date? = null
        if(datumKraj != null) dKraj = formatter.parse(datumKraj)
        return dKraj
    }

    fun dajDatumRada() : Date ?{
        val formatter = SimpleDateFormat("yyyy-mm-dd")
        var dRada: Date? = null
        if(datumRada != null) dRada = formatter.parse(datumRada)
        return dRada
    }


    fun getStatus() : String{
        if(dajDatumRada() != null) return "plava"
        else if(dajDatumKraj()!=null && dajDatumPocetak().before(Date()) && dajDatumKraj()!!.after(Date())) return "zelena"
        else if(dajDatumKraj()==null && dajDatumPocetak().before(Date())) return "zelena1"
        else if(dajDatumKraj()!=null && dajDatumKraj()!!.before(Date())) return "crvena"
        return "zuta"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Anketa

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }


    /*override fun equals(other: Any?): Boolean {
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
    }*/
}
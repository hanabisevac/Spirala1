package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Pitanje( @PrimaryKey val id : Int,
    //@ColumnInfo(name = "id") var id : Int,
                   @ColumnInfo(name = "naziv") val naziv : String,
                   @ColumnInfo(name = "tekstPitanja") val tekstPitanja : String,
                   @Ignore var opcije : MutableList<String>,
                   @ColumnInfo(name = "opcije") var stringOpcije : String) {
    constructor(id : Int, naziv : String, tekstPitanja: String, stringOpcije: String) : this( id, naziv, tekstPitanja, mutableListOf<String>(), stringOpcije){
        val splitano = stringOpcije.split(" ")
        opcije.addAll(splitano)
    }

}
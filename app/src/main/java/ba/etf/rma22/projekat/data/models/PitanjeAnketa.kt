package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//naziv je naziv pitanja
//anketa je naziv ankete
@Entity
data class PitanjeAnketa(@PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "PitanjeId") val PitanjeId : Int, //upitno da li je ovo primary key
    @ColumnInfo(name = "AnketumId") val AnketumId : Int) {


}
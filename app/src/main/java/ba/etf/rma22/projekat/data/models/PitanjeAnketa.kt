package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//naziv je naziv pitanja
//anketa je naziv ankete
@Entity
data class PitanjeAnketa(@ColumnInfo(name = "PitanjeId") val PitanjeId : Int, //upitno da li je ovo primary key
    @ColumnInfo(name = "AnketumId") val AnketumId : Int) {


    @PrimaryKey(autoGenerate = true) var id : Int = 0



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PitanjeAnketa

        if (PitanjeId != other.PitanjeId) return false
        if (AnketumId != other.AnketumId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = PitanjeId
        result = 31 * result + AnketumId
        return result
    }

}
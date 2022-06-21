package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Istrazivanje(
    @PrimaryKey var id : Int,
    @ColumnInfo(name = "naziv") val naziv : String,
    @ColumnInfo(name = "godina") val godina : Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Istrazivanje

        if (naziv != other.naziv) return false
        return true
    }

    override fun hashCode(): Int {
        var result = naziv.hashCode()
        result = 31 * result
        return result
    }
}
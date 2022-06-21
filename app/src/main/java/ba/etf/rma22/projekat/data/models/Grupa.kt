package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Grupa(
    @PrimaryKey @SerializedName("id") var id : Int,
    @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv : String,
    @ColumnInfo(name = "Istrazivanje") var nazivIstrazivanja : String ?,
    @ColumnInfo(name = "IstrazivanjeId") @SerializedName("IstrazivanjeId") val istrazivanjeId : Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Grupa

        if (id != other.id) return false
        if (naziv != other.naziv) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + naziv.hashCode()
        return result
    }
}
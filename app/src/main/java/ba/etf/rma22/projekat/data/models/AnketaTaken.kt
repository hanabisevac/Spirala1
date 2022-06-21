package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
@Entity
data class AnketaTaken(
    @PrimaryKey @SerializedName("id") val id : Int,
    @ColumnInfo(name = "student") @SerializedName("student") val student : String, //email studenta
    @ColumnInfo(name = "datumRada") @SerializedName("datumRada") var datumRada : String?,  //trenutni datum
    @ColumnInfo(name = "progres") @SerializedName("progres") var progres : Int, //procenat odg pitanja
    @ColumnInfo(name = "AnketumId") @SerializedName("AnketumId") val AnketumId : Int //id od ankete

) {
}
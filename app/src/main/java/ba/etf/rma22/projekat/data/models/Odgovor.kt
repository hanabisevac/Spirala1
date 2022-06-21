package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//OVO DOBIJAS KAO ODGOVOR
@Entity
data class Odgovor(@PrimaryKey(autoGenerate = true) val id : Int,
                   @ColumnInfo(name = "odgovoreno") @SerializedName("odgovoreno") val odgovoreno : Int, //pozicija odgovora
                   @ColumnInfo(name = "AnketaTakenId") @SerializedName("AnketaTakenId") val anketaTakenId : Int, //id od AnketaTaken
                   @ColumnInfo(name = "PitanjeId") @SerializedName("PitanjeId") val pitanjeId : Int //id od pitanja
                    ){

}
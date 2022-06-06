package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

//OVO DOBIJAS KAO ODGOVOR
data class Odgovor( val id : Int,
                   @SerializedName("odgovoreno") val odgovoreno : Int, //pozicija odgovora
                   @SerializedName("AnketaTakenId") val anketaTakenId : Int, //id od AnketaTaken
                   @SerializedName("PitanjeId") val pitanjeId : Int //id od pitanja
                    ){

}
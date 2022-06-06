package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

//OVO DOBIJAS KAO ODGOVOR
data class Odgovor(@SerializedName("id") val id : Int,
                    @SerializedName("odgovoreno") val odgovoreno : Int, //pozicija odgovora
                   @SerializedName("pitanje") val pitanjeId : Int,
                   @SerializedName("AnketaTakenId") val anketaTakenId : Int, //id od AnketaTaken
                    ){

}
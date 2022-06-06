package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class AnketaTaken(
    @SerializedName("id") val id : Int,
    @SerializedName("student") val student : String, //email studenta
    @SerializedName("datumRada") var datumRada : Date?,
    @SerializedName("progres") var progres : Int, //procenat odg pitanja
    @SerializedName("AnketumId") val AnketumId : Int

) {
}
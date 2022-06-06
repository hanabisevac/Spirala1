package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

//OVO SALJES KAO BODY
class OdgovorBody(@SerializedName("odgovor") val odgovor : Int,
    @SerializedName("pitanje") val pitanje : Int,
    @SerializedName("progres") val prog : Int
) {
}
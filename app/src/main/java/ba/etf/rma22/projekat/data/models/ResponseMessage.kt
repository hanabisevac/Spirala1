package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

class ResponseMessage(@SerializedName("message") val poruka : String) {
}
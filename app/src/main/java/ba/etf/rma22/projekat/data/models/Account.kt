package ba.etf.rma22.projekat.data.models

data class Account(var id : Int,
                    var student : String,
                    var acHash : String) {

    fun postaviHash(hash : String) {
        acHash = hash
    }

    fun dajHash() : String{
        return acHash
    }
}
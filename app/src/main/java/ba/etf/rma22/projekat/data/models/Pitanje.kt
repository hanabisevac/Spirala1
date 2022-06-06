package ba.etf.rma22.projekat.data.models

data class Pitanje(var id : Int, val naziv : String, val tekstPitanja : String, val opcije : List<String>) {
}
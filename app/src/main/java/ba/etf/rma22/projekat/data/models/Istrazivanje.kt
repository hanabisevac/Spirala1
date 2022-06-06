package ba.etf.rma22.projekat.data.models

data class Istrazivanje(
    var id : Int,
    val naziv : String,
    val godina : Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Istrazivanje

        if (naziv != other.naziv) return false
        return true
    }

    override fun hashCode(): Int {
        var result = naziv.hashCode()
        result = 31 * result
        return result
    }
}
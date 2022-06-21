package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(@PrimaryKey var id : Int,
                   @ColumnInfo(name = "student")var student : String,
                   @ColumnInfo(name ="acHash") var acHash : String) {

    fun postaviHash(hash : String) {
        acHash = hash
    }

    fun dajHash() : String{
        return acHash
    }
}
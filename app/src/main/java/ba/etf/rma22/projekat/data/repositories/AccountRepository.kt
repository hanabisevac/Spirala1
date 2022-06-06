package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Account


class AccountRepository {

    companion object{
        var student : Account = Account(14, "hbisevac1@etf.unsa.ba", "ecac5bf8-b81a-4b77-8887-e3264eb4d0bb")
        var acHash : String = "ecac5bf8-b81a-4b77-8887-e3264eb4d0bb"


        fun postaviHash(acc : String) : Boolean{
            if(acHash!="") return true
            acHash = acc
            student.postaviHash(acHash)
            return false
        }

    }

}
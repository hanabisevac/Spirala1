package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random


class AccountRepository {

    companion object{
        var student : Account = Account(14, "hbisevac1@etf.unsa.ba", "ecac5bf8-b81a-4b77-8887-e3264eb4d0bb")
        var acHash : String = "ecac5bf8-b81a-4b77-8887-e3264eb4d0bb"
        var context = ContextRepo.getContext()


        suspend fun postaviHash(acc : String) : Boolean{
            acHash = acc
            return withContext(Dispatchers.IO){
                val db = AppDatabase.getInstance(context)
                val trenutniAcc = db.accountDAO().getAccount()
                if(trenutniAcc == null) {
                    db.accountDAO().insertNovi(Account(14, "hbisevac1@etf.unsa.ba", acc))
                    return@withContext true
                }
                db.anketaTakenDAO().deleteAnketeTaken()
                db.anketaDAO().deleteAnkete()
                db.grupaDAO().deleteGrupe()
                db.istrazivanjeDAO().deleteIstrazivanja()
                db.odgovorDAO().deleteOdgovori()
                db.pitanjeAnketaDAO().deletePitanjaAnkete()
                db.pitanjeDAO().deletePitanja()
                if(acHash == trenutniAcc.acHash) return@withContext true
                db.accountDAO().deleteAccount()
                db.accountDAO().insertNovi(Account(Random.nextInt(), "Ime", acc))
                student.postaviHash(acHash)
                return@withContext true
            }
        }

        fun getHash() : String {
            return acHash
        }

    }

}
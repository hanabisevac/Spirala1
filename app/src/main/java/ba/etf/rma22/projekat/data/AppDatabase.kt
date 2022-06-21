package ba.etf.rma22.projekat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ba.etf.rma22.projekat.data.dao.*
import ba.etf.rma22.projekat.data.models.*


@Database(entities = arrayOf(Anketa::class, Account::class, AnketaTaken::class, Grupa::class, Istrazivanje::class, Odgovor::class, Pitanje::class, PitanjeAnketa::class), version=2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun anketaDAO() : AnketaDAO
    abstract fun accountDAO() : AccountDAO
    abstract fun anketaTakenDAO() : AnketaTakenDAO
    abstract fun grupaDAO() : GrupaDAO
    abstract fun istrazivanjeDAO() : IstrazivanjeDAO
    abstract fun odgovorDAO() : OdgovorDAO
    abstract fun pitanjeAnketaDAO() : PitanjeAnketaDAO
    abstract fun pitanjeDAO() : PitanjeDAO


    companion object{
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context : Context) : AppDatabase{
            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = buildRoomDB(context)
                }

            }
            return INSTANCE!!
        }
        private fun buildRoomDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "RMA22DB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}
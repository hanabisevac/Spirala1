package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.ResponseMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GrupaRepository {


   /* suspend fun listaGrupa() : List<Grupa>{
        val listaSve = IstrazivanjeIGrupaRepository.getGrupe()
        //provjerti da li za offset null zaista vraca sva istrazivanja
        val listaIstrazivanja = IstrazivanjeIGrupaRepository.getIstrazivanja()
        for(i in listaSve.indices){
            for(j in listaIstrazivanja.indices){
                if(listaSve[i].istrazivanjeId == listaIstrazivanja[j].id) listaSve[i].nazivIstrazivanja = listaIstrazivanja[j].naziv
            }
        }
        return listaSve
    }*/

    //upisi studenta u grupu
    /*suspend fun dodajStudenta(id : Int, hash : String) : ResponseMessage {
        return withContext(Dispatchers.IO){
            val response = ApiConfig.retrofit.dodajStudenta(1, hash)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }*/

    /*suspend fun getGroupsByIstrazivanje(nazivIstrazivanja : String) : List<Grupa> {
        val lista = listaGrupa()
        val nova = mutableListOf<Grupa>()
        for(i in lista.indices){
            if(lista[i].nazivIstrazivanja == nazivIstrazivanja) nova.add(lista[i])
        }
        return nova
    }

    suspend fun getAll() : List<Grupa> {
        return listaGrupa()
    }*/
}
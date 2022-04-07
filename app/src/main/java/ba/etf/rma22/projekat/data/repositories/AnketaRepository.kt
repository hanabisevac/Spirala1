package ba.etf.rma22.projekat.data.repositories


import ba.etf.rma22.projekat.data.models.Anketa
import com.example.spirala1.data.listaAnketa
import java.util.*

object AnketaRepository {

    fun getAll() : List<Anketa> {
        return listaAnketa()
    }

    fun getDone() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getAll()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada != null) lista.add(pomocna.get(i))
        }
        return lista
    }

    fun aktivneAnkete() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getAll()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada == null && pomocna.get(i).datumPocetka.before(Date()) && pomocna.get(i).datumKraj.after(Date())) lista.add(pomocna.get(i))
        }
        return lista
    }

    fun getNotTaken() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getAll()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada == null && pomocna.get(i).datumKraj.before(Date())) lista.add(pomocna.get(i))
        }
        return lista

    }
    
    fun getMyAnkete() : List<Anketa> {
        return KorisnikRepository.getAnkete()
    }

    fun getFuture() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getAll()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumPocetka.after(Date())) lista.add(pomocna.get(i))
        }
        return lista

    }



}
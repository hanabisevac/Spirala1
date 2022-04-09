package ba.etf.rma22.projekat.data.repositories


import ba.etf.rma22.projekat.data.models.Anketa
import com.example.spirala1.data.listaAnketa
import java.util.*

object AnketaRepository {

    fun getAll() : List<Anketa> {
        val korisnikove = KorisnikRepository.getAnkete()
        val sve = listaAnketa()
        val lista = mutableListOf<Anketa>()
        var ima : Int = 0
        for(i in 0..sve.size-1){
            for(j in 0..korisnikove.size-1){
                if(sve[i].equals(korisnikove[j])) ima=1
            }
            if(ima==1){
                ima=0
            }
            else{
                lista.add(sve[i])
            }
        }
        return lista
    }

    fun getDone() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = KorisnikRepository.getAnkete()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada != null) lista.add(pomocna.get(i))
        }
        return lista
    }

    fun aktivneAnkete() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = KorisnikRepository.getAnkete()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada == null && pomocna.get(i).datumPocetak.before(Date()) && pomocna.get(i).datumKraj.after(Date())) lista.add(pomocna.get(i))
        }
        return lista
    }

    fun getNotTaken() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = KorisnikRepository.getAnkete()
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
        val pomocna : List<Anketa> = KorisnikRepository.getAnkete()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumPocetak.after(Date())) lista.add(pomocna.get(i))
        }
        return lista

    }



}
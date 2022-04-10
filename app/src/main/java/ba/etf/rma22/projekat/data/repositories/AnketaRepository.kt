package ba.etf.rma22.projekat.data.repositories


import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import com.example.spirala1.data.listaAnketa
import java.util.*

object AnketaRepository {


    fun getAll() : List<Anketa> {
        val korisnikove = KorisnikRepository.getGrupe()
        val sve = listaAnketa()
        val lista = mutableListOf<Anketa>()
        var ima : Int = 0
        for(i in 0..sve.size-1){
            for(j in 0..korisnikove.size-1){
                if(sve[i].nazivIstrazivanja == korisnikove[j].nazivIstrazivanja && sve[i].nazivGrupe == korisnikove[j].naziv) ima=1
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
        val pomocna : List<Anketa> = getMyAnkete()
        val pomocna2 = dajAnketeSaIstrazivanjem()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada != null) lista.add(pomocna.get(i))
        }
        for(i in 0..pomocna2.size-1){
            if(pomocna2.get(i).datumRada != null) lista.add(pomocna2.get(i))
        }
        return lista
    }

    fun dajAnketeSaIstrazivanjem() : List<Anketa> {
        var lista = mutableListOf<Anketa>()
        val grupa = KorisnikRepository.getGrupe()
        val sve = getAll()
        var ima : Int = 0
        for(i in 0..sve.size-1) {
            for (j in 0..grupa.size - 1) {
                if (sve[i].nazivIstrazivanja == grupa[j].nazivIstrazivanja) ima=1
            }
            if(ima == 1){
                ima = 0
                lista.add(sve[i])
            }
        }
        return lista
    }


    fun aktivneAnkete() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getMyAnkete()
        val pomocna2 = dajAnketeSaIstrazivanjem()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada == null && pomocna.get(i).datumPocetak.before(Date()) && pomocna.get(i).datumKraj.after(Date())) lista.add(pomocna.get(i))
        }
        for(i in 0..pomocna2.size-1){
            if(pomocna2.get(i).datumRada == null && pomocna2.get(i).datumPocetak.before(Date()) && pomocna2.get(i).datumKraj.after(Date())) lista.add(pomocna2.get(i))
        }
        return lista
    }

    fun getNotTaken() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getMyAnkete()
        val pomocna2 = dajAnketeSaIstrazivanjem()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada == null && pomocna.get(i).datumKraj.before(Date())) lista.add(pomocna.get(i))
        }
        for(i in 0..pomocna2.size-1){
            if(pomocna2.get(i).datumRada == null && pomocna2.get(i).datumKraj.before(Date())) lista.add(pomocna2.get(i))
        }
        return lista

    }
    
    fun getMyAnkete() : List<Anketa> {
        val lista = KorisnikRepository.getGrupe()
        val ankete = listaAnketa()
        val nova = mutableListOf<Anketa>()
        var ima : Int = 0
        for(i in 0..ankete.size-1){
            for(j in 0..lista.size-1){
                if(lista[j].naziv == ankete[i].nazivGrupe && lista[j].nazivIstrazivanja == ankete[i].nazivIstrazivanja) ima=1;
            }
            if(ima == 1){
                ima = 0
                nova.add(ankete[i])
            }
        }
        return nova
    }

    fun getFuture() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = getMyAnkete()
        val pomocna2 = dajAnketeSaIstrazivanjem()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumPocetak.after(Date())) lista.add(pomocna.get(i))
        }
        for(i in 0..pomocna2.size-1){
            if(pomocna2.get(i).datumPocetak.after(Date())) lista.add(pomocna2.get(i))
        }
        return lista

    }


}
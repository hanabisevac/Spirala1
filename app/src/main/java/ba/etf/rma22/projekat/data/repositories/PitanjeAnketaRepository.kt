package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.view.dajSvaPitanjaAnketa

object PitanjeAnketaRepository {

    //vraca pitanja za anketu na koju korisnik klikne
    fun getPitanja(nazivAnkete : String, nazivIstrazivanje : String) : List<Pitanje>{
        val anketa = getAnketaSaIstrazivanjem(nazivAnkete, nazivIstrazivanje)
        val listaPitanjaZaAnketu = anketa?.let { getPitanjeAnketa(it) }
        val svaPitanja = PitanjaRepository.dajSvaPitanja()
        val pitanja = mutableListOf<Pitanje>()
        for(i in svaPitanja.indices){
                for(j in listaPitanjaZaAnketu?.indices!!) {
                    if(svaPitanja[i].naziv == listaPitanjaZaAnketu[j]) pitanja.add(svaPitanja[i])
                }
        }
        return pitanja
    }

    //vraca tu specijalnu Anketu za koju nam trebaju pitanja
    fun getAnketaSaIstrazivanjem(nazivAnkete: String, nazivIstrazivanje: String) : Anketa? {
        val ankete = AnketaRepository.getAll()
        for(i in ankete.indices){
            if(ankete[i].naziv==nazivAnkete && ankete[i].nazivIstrazivanja==nazivIstrazivanje) return ankete[i]
        }
        return null
    }

    //vraca sva pitanja koja pripadaju odredjenoj anketi
    fun getPitanjeAnketa(anketa : Anketa) : List<String>{
        val lista = getSvaPitanjaAnkete()
        val nova = mutableListOf<String>()
        for(i in lista.indices){
            if(lista[i].anketa == anketa.naziv && lista[i].istrazivanje == anketa.nazivIstrazivanja) nova.add(lista[i].naziv)
        }
        return nova
    }

    //vraca sva pitanja za sve ankete
    fun getSvaPitanjaAnkete() : List<PitanjeAnketa> {
        return dajSvaPitanjaAnketa()
    }


    fun getAnketaPoNazivuPitanja(naziv : String) : PitanjeAnketa? {
        val sve = getSvaPitanjaAnkete()
        var anketa : PitanjeAnketa? = null
        for(i in sve.indices){
            if(naziv == sve[i].naziv) anketa = sve[i]
        }
        return anketa
    }

    fun getPoNazivuPitanja(naziv : String) : Anketa? {
        val sve = AnketaRepository.getAll()
        var anketa : Anketa? = null
        for(i in sve.indices){
            if(naziv == sve[i].naziv) anketa = sve[i]
        }
        return anketa
    }

    fun dajPitanjaAnketa(anketa : String, istrazivanje : String) : List<PitanjeAnketa>{
        val sve = getSvaPitanjaAnkete()
        val lista = mutableListOf<PitanjeAnketa>()
        for(i in sve.indices){
            if(sve[i].anketa == anketa && sve[i].istrazivanje == istrazivanje) lista.add(sve[i])
        }
        return lista
    }




}
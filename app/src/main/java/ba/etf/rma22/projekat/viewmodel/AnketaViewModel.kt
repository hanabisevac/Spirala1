package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import java.util.*

class AnketaViewModel {

    fun getSveAnkete() : List<Anketa>{
        val lista : List<Anketa> = AnketaRepository.getAll()
        Collections.sort(lista)
        return lista
    }




    fun getMyAnkete() : List<Anketa> {
        val lista : List<Anketa> = AnketaRepository.getMyAnkete()
        Collections.sort(lista)
        return lista
    }

    fun getUradjeneAnkete() : List<Anketa>{
        val lista : List<Anketa> = AnketaRepository.getDone()
        Collections.sort(lista)
        return lista
    }

    fun getAktivneAnkete() : List<Anketa> {
        val lista : List<Anketa> = AnketaRepository.aktivneAnkete()
        Collections.sort(lista)
        return lista

    }


    fun getZavrseneAnkete() : List<Anketa> {
        val lista : List<Anketa> = AnketaRepository.getNotTaken()
        Collections.sort(lista)
        return lista


    }
    fun getSljedeceAnkete() : List<Anketa>{
        val lista : List<Anketa> = AnketaRepository.getFuture()
        Collections.sort(lista)
        return lista


    }

}
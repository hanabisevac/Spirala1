package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import java.util.*

class AnketaViewModel {

    fun getSveAnkete() : List<Anketa>{
        val lista : MutableList<Anketa> = AnketaRepository.getAll() as MutableList<Anketa>
        lista.sort()
        return lista
    }

    fun getMyAnkete() : List<Anketa> {
        val lista : MutableList<Anketa> = AnketaRepository.getMyAnkete() as MutableList<Anketa>
        lista.sort()
        return lista
    }

    fun getUradjeneAnkete() : List<Anketa>{
        val lista : MutableList<Anketa> = AnketaRepository.getDone() as MutableList<Anketa>
        lista.sort()
        return lista
    }

    fun getAktivneAnkete() : List<Anketa> {
        val lista : MutableList<Anketa> = AnketaRepository.aktivneAnkete() as MutableList<Anketa>
        lista.sort()
        return lista

    }

    fun getAktvineIBuduce() : List<Anketa> {
        val lista = mutableListOf<Anketa>()
        lista.addAll(getAktivneAnkete())
        lista.addAll(getSljedeceAnkete())
        lista.sort()
        return lista
    }


    fun getZavrseneAnkete() : List<Anketa> {
        val lista : MutableList<Anketa> = AnketaRepository.getNotTaken() as MutableList<Anketa>
        lista.sort()
        return lista


    }
    fun getSljedeceAnkete() : List<Anketa>{
        val lista : MutableList<Anketa> = AnketaRepository.getFuture() as MutableList<Anketa>
        lista.sort()
        return lista

    }

}
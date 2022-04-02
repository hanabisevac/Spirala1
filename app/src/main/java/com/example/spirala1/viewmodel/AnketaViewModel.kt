package com.example.spirala1.viewmodel

import com.example.spirala1.data.Anketa
import com.example.spirala1.data.AnketaRepository
import java.util.*

class AnketaViewModel {

    fun getSveAnkete() : List<Anketa>{
        val lista : List<Anketa> = AnketaRepository.getAll()
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
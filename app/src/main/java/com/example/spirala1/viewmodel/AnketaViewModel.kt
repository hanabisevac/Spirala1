package com.example.spirala1.viewmodel

import com.example.spirala1.data.Anketa
import com.example.spirala1.data.AnketaRepository
import java.util.*

class AnketaViewModel {

    fun getSveAnkete() : List<Anketa>{
        val lista : List<Anketa> = AnketaRepository.ankete()
        Collections.sort(lista)
        return lista
    }

    fun getUradjeneAnkete() : List<Anketa>{
        val lista : List<Anketa> = AnketaRepository.uradjeneAnkete()
        Collections.sort(lista)
        return lista
    }

    fun getAktivneAnkete() : List<Anketa> {
        val lista : List<Anketa> = AnketaRepository.aktivneAnkete()
        Collections.sort(lista)
        return lista

    }

    fun getZavrseneAnkete() : List<Anketa> {
        val lista : List<Anketa> = AnketaRepository.zavrseneAnkete()
        Collections.sort(lista)
        return lista


    }
    fun getSljedeceAnkete() : List<Anketa>{
        val lista : List<Anketa> = AnketaRepository.sljedeceAnkete()
        Collections.sort(lista)
        return lista


    }

}
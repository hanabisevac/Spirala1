package com.example.spirala1.data

import java.time.LocalDate
import java.util.*

object AnketaRepository {


    fun ankete() : List<Anketa> {
        return listaAnketa()
    }

    fun uradjeneAnkete() : List<Anketa> {
        var lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = ankete()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada != null) lista.add(pomocna.get(i))
        }
        return lista
    }

    fun aktivneAnkete() : List<Anketa> {
        var lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = ankete()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada == null && pomocna.get(i).datumPocetka.before(Date()) && pomocna.get(i).datumKraj.after(Date())) lista.add(pomocna.get(i))
        }
        return lista
    }

    fun zavrseneAnkete() : List<Anketa> {
        var lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = ankete()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumRada == null && pomocna.get(i).datumKraj.before(Date())) lista.add(pomocna.get(i))
        }
        return lista

    }

    fun sljedeceAnkete() : List<Anketa> {
        var lista = mutableListOf<Anketa>()
        val pomocna : List<Anketa> = ankete()
        for(i in 0..pomocna.size-1){
            if(pomocna.get(i).datumPocetka.after(Date())) lista.add(pomocna.get(i))
        }
        return lista

    }

}
package com.example.spirala1.data

import com.example.spirala1.Korisnik.dajKorisnika

object IstrazivanjeRepository {

    fun getIstrazivanjeByGodina(godina : Int) : List<Istrazivanje>{
        val nova = mutableListOf<Istrazivanje>()
        val lista : List<Istrazivanje> = listaIstrazivanja()
        for(i in 0..lista.size-1){
            if(lista[i].godina == godina) nova.add(lista[i])
        }
        return nova
    }

    fun getAll() : List<Istrazivanje> {
        return listaIstrazivanja()
    }

    fun getUpisani() : List<Istrazivanje> {
        val imena = dajKorisnika().istrazivanja
        val nova = mutableListOf<Istrazivanje>()
        val sve = getAll()
        for(i in 0..sve.size-1){
            for(j in 0..imena.size-1){
                if(sve[i].naziv == imena[i]) nova.add(sve[i])
            }
        }
        return nova
    }

}
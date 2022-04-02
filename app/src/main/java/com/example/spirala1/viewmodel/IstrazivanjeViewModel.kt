package com.example.spirala1.viewmodel

import com.example.spirala1.data.Istrazivanje
import com.example.spirala1.data.IstrazivanjeRepository

class IstrazivanjeViewModel {

    fun getIstrazivanjePoGodini(g : Int) : List<Istrazivanje>{
        return IstrazivanjeRepository.getIstrazivanjeByGodina(g)
    }
}